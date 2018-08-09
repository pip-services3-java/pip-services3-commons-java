# Basic portable abstractions for Pip.Services in Java Changelog

## <a name="2.0.0"></a> 2.0.0 (2017-02-24)

Cleaned up and simplified programming model.

### Features
* **build** Added Factory class

### Breaking Changes
* Refactored **refer** package. Removed IDescriptable and ILocateable interface. Made locator a mandatory requirement to place component into references.
* Moved **ManagedReferences** to **pip-services-container**


## <a name="1.0.0"></a> 1.0.0 (2016-09-22)

Initial public release

### Features
* **build** Component factories framework
* **commands** Command and Eventing patterns
* **config** Configuration framework
* **convert** Portable soft data converters
* **count** Performance counters components
* **data** Data value objects and random value generators
* **errors** Portable application errors
* **log** Logging components
* **random** Random data generators
* **refer** Component referencing framework
* **reflect** Portable reflection helpers
* **run** Execution framework
* **validate** Data validators

### Bug Fixes
No fixes in this version

