# 🚀 AutomationExercise Test Framework

A clean, professional Selenium-based test automation framework built with Java, TestNG, and Maven. This framework follows industry best practices and provides robust testing capabilities for the [Automation Exercise](https://automationexercise.com) web application.

## 📋 Table of Contents
- [Overview](#overview)
- [Framework Architecture](#framework-architecture)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [Framework Features](#framework-features)
- [Page Object Model](#page-object-model)
- [Utilities](#utilities)
- [Best Practices](#best-practices)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)

## 🎯 Overview

This automation framework is designed for comprehensive web application testing with a focus on maintainability, scalability, and reliability. It implements industry-standard design patterns including Page Object Model (POM), provides ExtentReports for detailed test reporting, and supports both UI and headless execution modes.

**Target Application:** [Automation Exercise](https://automationexercise.com)
**Test Coverage:** User registration, login/logout, homepage validation, and end-to-end user journeys

## 🏗️ Framework Architecture

The framework follows a modular, layered architecture with clear separation of concerns:

- **Base Layer**: Common setup, teardown, and configuration management
- **Page Layer**: Page Object Model implementation for web pages
- **Test Layer**: Test classes with business logic and assertions
- **Utility Layer**: Helper classes for common operations
- **Configuration Layer**: External configuration management
- **Reporting Layer**: Comprehensive test reporting and logging

## 🛠️ Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 24 | Programming Language |
| **Selenium WebDriver** | 4.33.0 | Web Automation |
| **TestNG** | 7.11.0 | Testing Framework |
| **Maven** | 3.9.9+ | Build Tool & Dependency Management |
| **ExtentReports** | 5.1.2 | Test Reporting |
| **WebDriverManager** | 6.1.0 | Driver Management |
| **Log4j** | 2.20.0 | Logging Framework |
| **Apache Commons IO** | 2.11.0 | File Operations |

## 📁 Project Structure

```
AutomationExercise/
├── src/
│   ├── main/java/com/akshay/automationexecrices/
│   │   ├── base/                    # Base test classes
│   │   │   └── BaseTest.java        # Common setup/teardown
│   │   ├── pages/                   # Page Object Model classes
│   │   │   ├── HomePage.java
│   │   │   ├── LoginPage.java
│   │   │   └── SignupPage.java
│   │   └── utils/                   # Utility classes
│   │       ├── CommonMethods.java   # Test data generation
│   │       ├── DriverManager.java   # WebDriver management
│   │       └── PropertyUtil.java    # Configuration management
│   └── test/
│       ├── java/                    # Test classes
│       │   ├── HomePageTest.java
│       │   ├── LoginFlowTest.java
│       │   └── SignupFlowTest.java
│       └── resources/               # Test resources
│           └── log4j2.xml          # Logging configuration
├── config/                         # Configuration files
│   └── config.properties           # Application configuration
├── test-output/                    # Test reports and screenshots
│   ├── AutomationExercise.html     # ExtentReports
│   └── screenshots/                # Failure screenshots
├── logs/                           # Application logs
├── target/                         # Maven build artifacts
├── AutomationExercise-TestSuite.xml # TestNG suite configuration
├── pom.xml                         # Maven dependencies
└── README.md                       # Project documentation
```

## 📋 Prerequisites

- ☕ **Java 24** or higher
- 🔧 **Maven 3.6+**
- 🌐 **Chrome Browser** (latest version)
- 💻 **IDE** (IntelliJ IDEA/Eclipse recommended)
- 🌍 **Internet Connection** (for WebDriverManager and dependencies)

## 🚀 Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd AutomationExercise
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Verify Setup
```bash
mvn clean compile
```

### 4. Run Sample Test
```bash
mvn test -Dtest=HomePageTest
```

## ⚙️ Configuration

### 📄 Configuration File
All test configurations are managed through `config/config.properties`. Update this file with your test environment details:

```properties
# Browser Configuration
browser=chrome
headless=false

# Application URL
url=https://automationexercise.com

# Test User Credentials (for existing user login tests)
username=your-test-email@domain.com
password=your-test-password

# User Registration Data (for new user signup tests)
userEmail=test-email@yopmail.com
userFirstName=TestFirstName
userLastName=TestLastName
userMobileNumber=1234567890
userStreet=Test Street
userCity=Test City
userState=Test State
userZipcode=123456
userPassword=test-password
```

### 🎯 TestNG Configuration
Test execution is configured through `AutomationExercise-TestSuite.xml`:

```xml
<suite name="AutomationExercise Test Suite" verbose="2" parallel="false" thread-count="1">
    <parameter name="browser" value="chrome"/>
    <parameter name="environment" value="QA"/>

    <test name="Smoke Tests" preserve-order="true" enabled="true">
        <classes>
            <class name="HomePageTest">
                <methods>
                    <include name="verifyHomePageTitle"/>
                </methods>
            </class>
        </classes>
    </test>

    <test name="Regression Tests" preserve-order="true" enabled="true">
        <classes>
            <class name="LoginFlowTest"/>
            <class name="SignupFlowTest"/>
        </classes>
    </test>
</suite>
```

## 🏃‍♂️ Running Tests

### 📊 Run All Tests
```bash
mvn clean test
```

### 🎯 Run Specific Test Suite
```bash
mvn clean test -DsuiteXmlFile=AutomationExercise-TestSuite.xml
```

### 🖥️ Run in Headless Mode
```bash
mvn clean test -Dheadless=true
```

### 🔍 Run Specific Test Class
```bash
mvn clean test -Dtest=LoginFlowTest
```

### 🎪 Run Specific Test Method
```bash
mvn clean test -Dtest=LoginFlowTest#verify_LoginSuccessfullyWithValidCredentials
```

### 🔄 Run Tests with Custom Browser
```bash
mvn clean test -Dbrowser=chrome
```

## 📊 Test Reports

### 📈 ExtentReports
- **Location:** `test-output/AutomationExercise.html`
- **Features:**
  - Detailed test execution logs with step-by-step documentation
  - System information and environment details
  - Test duration and execution statistics
  - Interactive charts and graphs
  - Professional presentation for stakeholders

### 📸 Screenshots
- **Location:** `test-output/screenshots/`
- **Features:**
  - Directory created automatically during test execution
  - Organized storage for any future screenshot implementations

### 📋 Surefire Reports
- **Location:** `target/surefire-reports/`
- **Format:** XML and TXT reports for CI/CD integration

### 📝 Logs
- **Location:** `logs/test-log.log`
- **Level:** Configurable via `log4j2.xml`

### 🌐 Viewing Reports
Open the ExtentReports HTML file directly:
```bash
# Open the report file in your browser
test-output/AutomationExercise.html
```

## ✨ Framework Features

### 🎭 Page Object Model (POM)
- **Maintainable:** Easy to update locators and methods
- **Reusable:** Page methods can be used across multiple tests
- **Readable:** Clear separation of test logic and page interactions
- **Scalable:** Easy to add new pages and functionality
- **PageFactory Integration:** Efficient element initialization

### 🔧 Driver Management
- **Auto-configuration:** Automatic browser setup with WebDriverManager
- **Headless Support:** Configurable UI/headless execution modes
- **Thread-safe:** Proper WebDriver lifecycle management
- **Browser Mode Reporting:** Clear indication of execution mode

### 📊 Comprehensive Reporting
- **ExtentReports:** Rich HTML reports with detailed test execution logs
- **Detailed Logging:** Step-by-step execution tracking with business context
- **Test Metrics:** Pass/Fail statistics and execution time
- **Professional Presentation:** Business-ready reports for stakeholders

### 🎲 Advanced Test Data Generation
- **Dynamic Data:** Random generation of realistic test data
- **Indian Context:** Realistic Indian names, addresses, and phone numbers
- **State-City Mapping:** Geographically accurate address combinations
- **Utility Methods:** Convenient helper methods for data creation
- **Configurable Patterns:** Easy to modify data generation rules

### 🔒 Security Features
- **Credential Management:** Secure handling of sensitive data
- **Configuration Externalization:** No hardcoded credentials in code
- **Property-based Configuration:** Environment-specific settings

## 🏛️ Page Object Model

### 🏠 HomePage
Handles home page interactions and navigation:
- **Page title verification** for application validation
- **Login/Signup navigation** to authentication pages
- **User authentication status** validation after login
- **Logged-in username display** verification

### 🔐 LoginPage
Manages user authentication functionality:
- **User login** with email and password credentials
- **Error message validation** for invalid login attempts
- **Login form interactions** with proper wait strategies
- **Logout functionality** for session termination
- **Login page title verification**

### ✍️ SignupPage
Handles comprehensive user registration processes:
- **Initial signup** with name and email
- **Complete registration form** with all user details
- **Account creation confirmation** message validation
- **Account deletion functionality** for cleanup
- **Form field validation** and error handling

## 🛠️ Utilities

### 🎲 CommonMethods
- **Random Email Generation:** Unique email addresses for testing
- **Indian Name Generation:** Realistic first and last names
- **Address Generation:** Streets, cities, states with geographical accuracy
- **Phone Number Generation:** Valid Indian mobile number formats
- **Password Generation:** Secure random passwords
- **State-City Mapping:** Geographically correct combinations

### ⚙️ DriverManager
- **Browser Initialization:** Automated WebDriver setup and configuration
- **Headless Mode Support:** Configurable UI/headless execution
- **Chrome Options Management:** Optimized browser settings
- **Browser Mode Reporting:** Clear indication of execution mode
- **Lifecycle Management:** Proper driver creation and cleanup

### 🔧 PropertyUtil
- **Configuration Management:** Singleton pattern implementation
- **Properties File Loading:** Efficient file reading and caching
- **Key-Value Access:** Easy configuration parameter retrieval
- **Error Handling:** Robust file loading with exception management

## 📚 Best Practices

### ✅ Code Quality
- **Clean Code:** Readable, maintainable, and well-documented
- **Design Patterns:** Proper implementation of Singleton, Page Object Model
- **Error Handling:** Comprehensive exception management
- **Resource Management:** Proper cleanup and resource disposal
- **Consistent Naming:** Clear and descriptive method/variable names

### ✅ Test Design
- **Descriptive Names:** Clear test method and class naming conventions
- **Single Responsibility:** Each test focuses on one specific functionality
- **Independent Tests:** No dependencies between test methods
- **Data-Driven:** Externalized test data and configuration
- **Business Context:** Tests reflect real user scenarios

### ✅ Selenium Best Practices
- **Explicit Waits:** WebDriverWait for reliable element interactions
- **Page Factory:** Efficient element initialization and management
- **Locator Strategy:** Robust and maintainable element selectors
- **Browser Management:** Proper WebDriver lifecycle handling
- **Thread Safety:** ThreadLocal usage for parallel execution

### ✅ Reporting Standards
- **Detailed Logging:** Step-by-step execution documentation
- **Visual Evidence:** Screenshots for failure analysis and debugging
- **Metrics Tracking:** Test execution statistics and trends
- **Professional Format:** Business-ready report presentation
- **Security Conscious:** Sensitive data masking in reports

## 🚨 Troubleshooting

### 🔧 Common Issues

#### Report Display Issues
**Problem:** ExtentReports not displaying properly
**Solution:** Ensure the HTML file opens correctly in your browser:
```bash
# Open the report file directly
test-output/AutomationExercise.html
```

#### WebDriver Issues
**Problem:** Browser fails to start or crashes during execution
**Solutions:**
- Ensure Chrome browser is updated to the latest version
- Check WebDriverManager logs for driver download issues
- Verify system PATH and permissions
```bash
mvn clean test -Dwebdriver.chrome.verboseLogging=true
```

#### Configuration Problems
**Problem:** Tests fail due to missing or incorrect configuration
**Solutions:**
- Verify `config/config.properties` file exists and is readable
- Check all required properties are defined
- Validate URL accessibility and credentials
```bash
# Check if config file exists and is readable
ls -la config/config.properties
```

#### Build Failures
**Problem:** Maven compilation or dependency issues
**Solutions:**
```bash
# Clean and rebuild the project
mvn clean compile
mvn dependency:resolve

# Check for dependency conflicts
mvn dependency:tree
```

#### Headless Mode Issues
**Problem:** Tests behave differently in headless vs UI mode
**Solutions:**
- Verify headless configuration in `config.properties`
- Check window size settings for headless mode
- Ensure all elements are properly loaded before interaction

### 📞 Getting Help
1. **Check Logs:** Review `logs/test-log.log` for detailed error information
2. **ExtentReports:** Check the HTML report for step-by-step execution details
3. **Configuration:** Verify all settings in `config/config.properties`
4. **Prerequisites:** Ensure all required software is installed and updated
5. **Dependencies:** Verify Maven dependencies are resolved correctly

## 🤝 Contributing

### 📋 Guidelines
1. **Fork** the repository and create a feature branch
2. **Follow** existing coding standards and conventions
3. **Add** appropriate tests for new functionality
4. **Update** documentation for any changes
5. **Test** thoroughly before submitting pull requests

### 🎯 Development Standards
- Follow Java naming conventions and best practices
- Add comprehensive JavaDoc comments for public methods
- Maintain consistent code formatting and indentation
- Include unit tests for utility methods and new features
- Update README and documentation for new capabilities

### 🔄 Pull Request Process
1. Create a feature branch: `git checkout -b feature/amazing-feature`
2. Make your changes and commit: `git commit -m 'Add amazing feature'`
3. Push to the branch: `git push origin feature/amazing-feature`
4. Open a Pull Request with detailed description
5. Ensure all tests pass and code review is complete

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

**Akshay** - Senior Automation Test Engineer

## 🙏 Acknowledgments

- **Selenium WebDriver** community for excellent automation tools
- **TestNG** framework contributors for robust testing capabilities
- **ExtentReports** developers for professional reporting solutions
- **Maven** ecosystem maintainers for dependency management
- **Automation Exercise** website for providing a comprehensive testing platform

## 📞 Support

For questions, issues, or contributions:
- Create an issue in the repository
- Review the troubleshooting section
- Check existing documentation and guides
- Follow the contributing guidelines for enhancements

---

**Happy Testing! 🎉**

*This framework represents professional-grade automation engineering and serves as an excellent template for enterprise test automation projects.*
