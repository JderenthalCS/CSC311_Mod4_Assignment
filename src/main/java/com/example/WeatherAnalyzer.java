package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Represents a single weather data entry using Java Records.
 *
 * @param date The date of the entry
 * @param temperature The recorded temperature
 * @param humidity The recorded humidity
 * @param precipitation The recorded precipitation
 */
record WeatherData(String date, double temperature, double humidity, double precipitation) {}

/**
 * Functional interface for processing weather data.
 */
@FunctionalInterface
interface WeatherProcessor {
    List<WeatherData> process(List<WeatherData> data);
}

public class WeatherAnalyzer {

    /**
     * Parses a CSV file and converts it into a list of WeatherData records.
     *
     * @param filePath Path to the CSV file.
     * @return List of WeatherData records.
     * @throws IOException If the file cannot be read.
     */
    static List<WeatherData> parseCSV(String filePath) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(filePath))) {
            return lines.skip(1) // Skip header
                    .map(line -> line.split(","))
                    .map(parts -> new WeatherData(parts[0], Double.parseDouble(parts[1]),
                            Double.parseDouble(parts[2]), Double.parseDouble(parts[3])))
                    .collect(Collectors.toList());
        }
    }

    /**
     * Calculates the average temperature for a specific month.
     *
     * @param data The list of weather data records.
     * @param month The month to filter by (e.g., "2025-02").
     * @return The average temperature.
     */
    static double averageTemperatureForMonth(List<WeatherData> data, String month) {
        return data.stream()
                .filter(d -> d.date().startsWith(month))
                .mapToDouble(WeatherData::temperature)
                .average()
                .orElse(Double.NaN);
    }

    /**
     * Filters and counts the number of rainy days (precipitation > 0mm).
     *
     * @param data The list of weather data records.
     * @return The count of rainy days.
     */
    static long countRainyDays(List<WeatherData> data) {
        return data.stream()
                .filter(d -> d.precipitation() > 0)
                .count();
    }

    /**
     * Filters days with temperatures above a given threshold.
     *
     * @param data The list of weather data records.
     * @param threshold The temperature threshold.
     * @return A list of WeatherData records that match the criteria.
     */
    static List<WeatherData> daysAboveTemperature(List<WeatherData> data, double threshold) {
        return data.stream()
                .filter(d -> d.temperature() > threshold)
                .collect(Collectors.toList());
    }

    /**
     * Categorizes temperature into "Hot", "Warm", or "Cold" using an enhanced switch.
     *
     * @param temperature The temperature to categorize.
     * @return The category as a string.
     */
    static String categorizeTemperature(double temperature) {
        return switch ((int) temperature / 10) {
            case 3, 4 -> "Hot";
            case 2 -> "Warm";
            default -> "Cold";
        };
    }
    /**
     * This method serves as the entry point for the Weather Data Analyzer application.
     * It parses weather data from a CSV file, processes the data, and prints various weather insights.
     *
     * ### Features Demonstrated:
     * - Computes the average temperature for February 2025.
     * - Identifies days with temperatures above 30°C.
     * - Counts the number of rainy days.
     * - Categorizes a sample temperature (25°C) using an **enhanced switch statement**.
     *
     * Example Output:
     * Average Temperature (Feb 2025): 18.78
     * Days Above 30C: [WeatherData[date=2025-01-04, temperature=35.9, humidity=99.0, precipitation=0.0], ...]
     * Rainy Days: 18
     * Temperature Category (25C): Warm
     *
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            // Example CSV File Path
            String filePath = "src/main/resources/weather_data.csv";
            List<WeatherData> data = parseCSV(filePath);

            // Example Usage
            System.out.println("Average Temperature (Feb 2025): " + averageTemperatureForMonth(data, "2025-02"));
            System.out.println("Days Above 30C: " + daysAboveTemperature(data, 30));
            System.out.println("Rainy Days: " + countRainyDays(data));

            // Example Categorization
            System.out.println("Temperature Category (25C): " + categorizeTemperature(25));

        } catch (IOException e) {
            System.err.println("Error reading weather data: " + e.getMessage());
        }
    }
}
