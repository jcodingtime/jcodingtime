<!-- PROJECT LOGO -->
<br />
<p align="center">
  <h3 align="center">JCodingTime</h3>
  <p align="center">
   A generate unit test for JHipster generated applications.
    <br />
  </p>
</p>


<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#pre">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>

<!-- GETTING STARTED -->
## Getting Started

### Installation

1. Get a compacted JAR's [this link](https://drive.google.com/file/d/1AUP1yBz-uFkmiRW3Hlk6eD6IfB2NIsBX/view?usp=sharing).

2. Unzip the JAR's files in root directory of JHipster project.

<!-- USAGE EXAMPLES -->
## Usage

1. Add annotations to methods that you wish generate unit tests. 

    For example:
```java
 public class Example {
	
	@JCodingTime
	@Input(firstParam=5, secondParam=5)
	@Output(result=25)
	public static int multiplyTwoNumbers(int firstParameter, int secondParameter) {
		return firstParameter * secondParameter;
	}
	
	@JCodingTime
	@Input(firstParam=2, secondParam=2)
	@Output(result=4)
	public static int sumTwoNumbers(int firstParameter, int secondParameter) {
		return firstParameter + secondParameter;
	}
	
	@JCodingTime
	@LimitValue(innerBoundary=0, upperBoundary=130)
	public static int setAge(int age) {
		return age;
	}
	
}
   ```

2. At the terminal, run the command below with the **relative path of java file** you want to test.

 ```sh
 java -classpath "JCodingTime.jar:commons-io-2.8.0.jar" jcodingtime.java.Main RELATIVE_PATH_HERE
   ```
   
3. See the file test generate on test folder of your project.

4. Adapts the tests according to the project and its due libraries.

5. Run the tests.

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[license-url]: https://github.com/jcodingtime/tcc-jcodingtime/blob/v1.0.0/LICENSE
