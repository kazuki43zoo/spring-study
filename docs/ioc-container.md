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

IoC Containerを構築するためのメタデータは、bean定義ファイル、コンフィグレーションクラス、アノテーションを使って定義することができます。

メタデータの定義例は以下の通りです。ここでは`TestBean`というクラスをBean定義する最もシンプルな定義例を紹介します。

* Bean

```java
package com.github.kazuki43zoo.container;

public class TestBean {
    public void print() {
        System.out.println("Hello World.");
    }
}
```

#### bean定義ファイル

bean定義ファイルと呼ばれるXMLファイルにメタデータを定義し、IoC Container(`ApplicationContext`)を構築する場合は、
以下のような実装になります。(伝統的な定義方法)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
       ">

    <bean id="testBean" class="com.github.kazuki43zoo.container.TestBean" />

</beans>
```

#### コンフィグレーションクラス

アノテーション(`@Configuration`)が付与されたコンフィギュレーションクラスにメタデータを定義し、IoC Container(`ApplicationContext`)を構築する場合は、
以下のような実装になります。(Spring 3からサポートされたモダンな定義方法)

```java
package com.github.kazuki43zoo.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // ★
public class AppConfig {

    @Bean // ★
    TestBean testBean() {
        return new TestBean();
    }

}
```

#### アノテーション

アノテーション(`@Component`)を付与してメタデータを定義し、IoC Container(`ApplicationContext`)を構築する場合は、
以下のような実装になります。(Spring 2.5からサポートされた定義方法)

```java
@Component // ★
public class TestBean {
    public void print() {
        System.out.println("Hello World.");
    }
}
```

コンポーネントのスキャン機能を有効にします。

```java
@Configuration
@ComponentScan // ★
public class AppConfig {
    // ...
}
```

or

```xml
<context:component-scan base-package="com.github.kazuki43zoo.container"/>
```

### Instantiating a container


### Using the container


## Bean overview
