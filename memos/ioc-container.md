# The IoC Container

## Introduction to the Spring IoC container and beans

IoCは「Inversion of Control」の略で、DI(dependency injection)と呼ぶこともあります。
IoC(DI)は、あるオブジェクトが別のオブジェクトとの依存関係を解決するプロセスの一つで、このプロセスを実行する実行環境のことをIoC(DI) Containerと呼びます。

SpringのIoC Containerは、以下のパッケージのコンポーネントで構成されます。

* `org.springframework.beans`
* `org.springframework.context`

SpringのIoC Containerの構成コンポーネントのうちもっとも重要なインタフェースは以下の２つです。

* `org.springframework.beans.factory.BeanFactory`
* `org.springframework.context.ApplicationContext`

簡単にいうと、

* `BeanFactory`はIoC Containerの基本的な機能を提供するインタフェース
* `ApplicationContext`は`BeanFactory`のスーパーセット(`BeanFactory`の子インタフェース)で、エンタープライズ向けアプリケーションで必要となる機能(AOP, メッセージ管理, イベント通知など)が追加されているインタフェース

です。

IoC Containerは、IoC Containerを構築するためのメタデータを読み込み、メタデータに基づきコンテナ内で管理するオブジェクトのインスタンス生成やオブジェクト間の依存関係の解決などの処理を行います。
なお、IoC Containerで管理するオブジェクトは「Bean」と呼びます。


## Container overview

SpringのIoC Containerは、`ApplicationContext`インタフェースで表現されます。
`ApplicationContext`は、IoC Containerで管理されているBeanを操作するためのメソッドを提供しており、
以下の実装クラスがSpringから提供されています。


**[非Web依存]**

* **`FileSystemXmlApplicationContext`** (\*1)
* **`ClassPathXmlApplicationContext`** (\*1)
* **`AnnotationConfigApplicationContext`** (\*2)
* `GenericGroovyApplicationContext` (\*3)
* `GenericApplicationContext`
* `GenericXmlApplicationContext`
* `StaticApplicationContext`

**[Web依存]**

* **`XmlWebApplicationContext`** (\*1)
* **`AnnotationConfigWebApplicationContext`** (\*2)
* `GroovyWebApplicationContext` (\*3)
* `GenericWebApplicationContext`
* `StaticWebApplicationContext`

\*1 : bean定義ファイルと呼ばれるXMLファイルからメタデータを読み取ってIoC Containerを構築する実装クラス

\*2 : アノテーション(`@Configuration`)が付与されたコンフィギュレーションクラスからメタデータを読み取ってIoC Containerを構築する実装クラス

\*3 : Groovyスクリプトからメタデータを読み取ってIoC Containerを構築する実装クラス


### Configuration metadata

IoC Containerを構築するためのメタデータは、

* bean定義ファイル
* コンフィグレーションクラス
* アノテーション

を使って定義することができます。

この3つの定義方法は共存することができますが、

* bean定義ファイル + アノテーション
* コンフィギュレーションクラス + アノテーション

の組み合わせで使うのが一般的です。

> **Note:**
>
> Springが提供しているコンポーネントやOSSライブラリから提供されているコンポーネントをbean定義ファイルやコンフィギュレーションクラスに定義し、アプリケーションを構築するためのコンポーネント(自分達で作成するコンポーネント)はアノテーションを使うというのが一般的だと思います。
> ただし、AP基盤系の共通コンポーネント(カスタマイズポイントがある or 必要なコンポーネント)については、bean定義ファイルやコンフィギュレーションクラスに定義するスタイルにしておいた方が無難です。

メタデータの定義例は以下の通りです。ここでは`TestBean`というクラスをBean定義する最もシンプルな定義例を紹介します。
なお、Beanのスコープ・優先順位・依存関係の解決などに関するメタデータの定義方法は別途紹介します。

**BeanとしてIoC Containerに登録するクラス**

```java
package com.github.kazuki43zoo.container;

public class TestBean {
    public void print() {
        System.out.println("Hello World.");
    }
}
```

**bean定義ファイル(伝統的な定義方法)**

bean定義ファイルと呼ばれるXMLファイルにメタデータを定義してIoC Container(`ApplicationContext`)を構築する場合は、
以下のような実装になります。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
       ">

    <!-- ★ beanを登録するためのタグを使ってbean定義 -->
    <bean id="testBean" class="com.github.kazuki43zoo.container.TestBean" />

</beans>
```

**コンフィグレーションクラス(Spring 3からサポートされたモダンな定義方法)**

アノテーション(`@Configuration`)が付与されたコンフィギュレーションクラスにメタデータを定義してIoC Container(`ApplicationContext`)を構築する場合は、
以下のような実装になります。

```java
package com.github.kazuki43zoo.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // ★ コンフィギュレーションクラスであることを示すアノテーションを付与
public class AppConfig {

    @Bean // ★ Bean定義用のメソッドであることを示すアノテーションを付与
    TestBean testBean() {
        return new TestBean();
    }

}
```

**アノテーション(Spring 2.5からサポートされた定義方法)**

Beanとして扱うクラス自体にアノテーションを付与することでメタデータを定義してIoC Container(`ApplicationContext`)を構築する場合は、
以下のような実装になります。

```java
@Component // ★ コンポーネント(Bean)であることを示すアノテーションを付与
public class TestBean {
    public void print() {
        System.out.println("Hello World.");
    }
}
```

```java
@Configuration
@ComponentScan // ★ コンポーネントのスキャン機能を有効にするためのアノテーションを付与
public class AppConfig {
    // ...
}
```

or

```xml
<!-- ★ コンポーネントのスキャン機能を有効にするためのタグを追加 -->
<context:component-scan base-package="com.github.kazuki43zoo.container"/>
```

### Instantiating a container

SpringのIoC Containerの生成は、`ApplicationContext`インタフェースの実装クラスのインスタンスを生成することで実現します。

```java
// ★ コンフィギュレーションクラスに定義されているメタデータを読み取ってIoC Containerを生成
ConfigurableApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);
```

```java
// ★ bean定義ファイルに定義されているメタデータを読み取ってIoC Containerを生成
ConfigurableApplicationContext context =
        new ClassPathXmlApplicationContext("applicationContext.xml");
```

### Shutting down the Spring IoC container

SpringのIoC Containerを使ったアプリケーションを終了する場合は、IoC Containerを停止する必要があります。

> **Note:**
>
> WebアプリケーションでIoC Containerを使う場合は、Springが提供している`ContextLoaderListener`(`ServletContextListener`インタフェースの実装クラス)によって停止処理を行ってくれます。

IoC Containerを停止する場合は、`ConfigurableApplicationContext`インタフェースに定義されている`close()`メソッドを呼び出す必要があります。
`close()`メソッドを呼び出しは直接行うのではなくJVMのシャットダウンフックを使ってください。
なお、SpringはJVMのシャットダウンフックに`close()`メソッドの呼び出しを登録するためのメソッドとして、
`ConfigurableApplicationContext`インタフェースに`registerShutdownHook()`メソッドを用意しています。

```java
ConfigurableApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);
context.registerShutdownHook(); // ★ JVMのシャットダウンフックに停止処理の呼び出しを登録
// ...
```

### Using the container

SpringのIoC Containerに登録したBeanを使う場合は、`ApplicationContext`インタフェースの`T getBean(String, Class<T>)`メソッドを使用します。

> **Note:**
>
> Spring MVC(Webアプリケーション用のMVCフレームワーク)やSpring Batch(バッチアプリケーション用のフレームワーク)上でアプリケーションを開発する場合は、`ApplicationContext`のメソッドを直接使用する必要はなく、Dependency Injection(DI)の仕組みを使用して必要なBeanを取得します。
> エントリーポイントとなるBeanの取得とメソッドの呼び出しは、フレームワーク側の機能で行ってくれます。

```java
ConfigurableApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);
context.registerShutdownHook();
TestBean testBean = context.getBean("testBean", TestBean.class); // ★ Beanの取得
testBean.print(); // ★ 取得したBeanの呼び出し
```

## Bean overview
