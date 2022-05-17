# <img src="https://uploads-ssl.webflow.com/5ea5d3315186cf5ec60c3ee4/5edf1c94ce4c859f2b188094_logo.svg" alt="Pip.Services Logo" width="200"> <br/> Portable Abstractions and Patterns for Java

This module is a part of the [Pip.Services](http://pipservices.org) polyglot microservices toolkit.
It provides a set of basic patterns used in microservices or backend services.
Also the module implemenets a reasonably thin abstraction layer over most fundamental functions across
all languages supported by the toolkit to facilitate symmetric implementation.

The module contains the following packages:

- **Commands** - Commanding and Eventing patterns
- **Config** - configuration framework
- **Convert** - portable soft value converters
- **Data** - data value objects and random value generators
- **Errors** - portable application errors
- **Random** - random data generators
- **Refer** - component referencing framework
- **Reflect** - portable reflection helpers
- **Run** - execution framework
- **Validate** - data validators

<a name="links"></a> Quick links:

* [Configuration Pattern](http://docs.pipservices.org/toolkit/getting_started/configurations/)
* [Locator Pattern](http://docs.pipservices.org/toolkit/recipes/component_references/)
* [Component Lifecycle](http://docs.pipservices.org/toolkit/recipes/component_lifecycle/)
* [Data Patterns](http://docs.pipservices.org/toolkit/recipes/memory_persistence/)
* [API Reference](https://pip-services3-java.github.io/pip-services3-commons-java/)
* [Change Log](CHANGELOG.md)
* [Get Help](http://docs.pipservices.org/get_help/)
* [Contribute](http://docs.pipservices.org/contribute/)

## Use

Go to the pom.xml file in Maven project and add dependencies::
```xml
<dependency>
  <groupId>org.pipservices3</groupId>
  <artifactId>pip-services3-commons</artifactId>
  <version>3.1.0</version>
</dependency>
```
Then you are ready to start using the Pip.Services patterns to augment your backend code.

For instance, here is how you can implement a component, that receives configuration, get assigned references,
can be opened and closed using the patterns from this module.

```java
package org.pipservices3.commons;

import org.pipservices3.commons.config.ConfigParams;
import org.pipservices3.commons.config.IConfigurable;
import org.pipservices3.commons.errors.ApplicationException;
import org.pipservices3.commons.errors.ConfigException;
import org.pipservices3.commons.refer.IReferenceable;
import org.pipservices3.commons.refer.IReferences;
import org.pipservices3.commons.refer.ReferenceException;
import org.pipservices3.commons.run.IOpenable;

public class MyComponentA implements IConfigurable, IReferenceable, IOpenable {

    private String _param1 = "ABC";
    private int _param2 = 123;
    private MyComponentB _anotherComponent;
    private boolean _opened = true;

    @Override
    public void configure(ConfigParams config) throws ConfigException {
        this._param1 = config.getAsStringWithDefault("param1", this._param1);
        this._param2 = config.getAsIntegerWithDefault("param2", this._param2);
    }

    @Override
    public void setReferences(IReferences references) throws ReferenceException, ConfigException {
        this._anotherComponent = refs.getOneRequired<MyComponentB>(
                new Descriptor("myservice", "mycomponent-b", "*", "*", "1.0")
        );
    }

    @Override
    public boolean isOpen() {
        return this._opened;
    }

    @Override
    public void open(String correlationId) throws ApplicationException {
        this._opened = true;
        System.out.println("MyComponentA has been opened.");
    }

    @Override
    public void close(String correlationId) throws ApplicationException {
        this._opened = true;
        System.out.println("MyComponentA has been closed.");
    }
}

```

Then here is how the component can be used in the code

```java
import org.pipservices3.commons.config.ConfigParams;
import org.pipservices3.commons.errors.ApplicationException;
import org.pipservices3.commons.errors.ConfigException;
import org.pipservices3.commons.refer.Descriptor;
import org.pipservices3.commons.refer.References;

public class MainClass {
    public static void main(String[] args) throws ApplicationException {
        MyComponentA myComponentA = new MyComponentA();

        // Configure the component
        myComponentA.configure(ConfigParams.fromTuples(
                "param1", "XYZ",
                "param2", 987
        ));

        // Set references to the component
        myComponentA.setReferences(References.fromTuples(
                new Descriptor("myservice", "mycomponent-b", "default", "default", "1.0"),
                myComponentB
        ));

        // Open the component
        myComponentA.open("123");
        System.out.println("MyComponentA has been opened.");
    }
}
```

## Develop

For development you shall install the following prerequisites:
* Java SE Development Kit 8+
* Eclipse Java Photon or another IDE of your choice
* Docker
* Apache Maven

Build the project:
```bash
mvn install
```

Run automated tests:
```bash
mvn test
```

Generate API documentation:
```bash
./docgen.ps1
```

Before committing changes run dockerized build and test as:
```bash
./build.ps1
./test.ps1
./clear.ps1
```

## Contacts

The initial implementation is done by **Sergey Seroukhov**. Pip.Services team is looking for volunteers to 
take ownership over Java implementation in the project.
