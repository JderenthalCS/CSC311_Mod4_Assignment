# Weather Data Analyzer

## Overview
The **Weather Data Analyzer** is a Java-based application that processes and analyzes weather data from a CSV file. It utilizes modern Java features, including:

- **Records** (Java 14+)
- **Enhanced switch statements** (Java 14+)
- **Text Blocks** (Java 15+)
- **Streams and Lambdas** for efficient data processing
- **Pattern Matching** for modern Java optimizations
- **Markdown Javadoc** (Java 18+)

## Features
- **Parse weather data** (temperature, humidity, precipitation) from a CSV file
- **Calculate average temperature** for a specific month
- **List days with temperatures above a given threshold**
- **Count rainy days**
- **Classify temperatures** using an enhanced switch statement

## Installation & Setup
### Prerequisites
- **Java 17+** (Recommended: Java 23)
- **Maven** (if using a Maven project)
- **IntelliJ IDEA / VS Code / Terminal**

## CSV Format
Ensure your `weather_data.csv` follows this format:
```
Date,Temperature,Humidity,Precipitation
2025-01-01,25.5,45.0,0.0
2025-01-02,10.2,55.3,5.1
```

## Example Output
```
Average Temperature (Feb 2025): 18.78
Days Above 30C: [WeatherData[date=2025-01-04, temperature=35.9, humidity=99.0, precipitation=0.0], ...]
Rainy Days: 18
Temperature Category (25C): Warm
```


