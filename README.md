# EarthquakeVisualizer

Fetching and plotting earthquakes from the USGS feed.

This Java application fetches earthquake data from the USGS website and 
puts the earthquakes on a map. The background imagery-composite of the 
map is delivered by the provider of Microsoft. The size and color of
each marker reflects the magnitude of the earthquake represented. To
prevent the map from being overloaded, a minimum threshold for the
magnitude of the earthquakes shown can be set (by default set to 2.5). 

## Installation

Download the EarthquakeVisualizer.jar file and launch it as a normal application (tested on OSX and Windows).
Alternatively download the src and lib folder and import it as a project in eclipse.

## Usage

Start the programm and explore Earth's earthquake activity of the last week.
By hovering over earthquakes additional information (place description, time and date) pops up, clicking on the map shows the current coordinates of the cursor in decimal degrees (WGS84). The map gets reloaded every 5 minutes.

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## Authors

* **Merlin Unterfinger** - *Initial work* - [munterfi](https://github.com/munterfi)

## Built With

* [eclipse](https://eclipse.org) - Integrated development environment (IDE) used
* [JDK 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) - Java SE Development Kit 8
* [unfolding](http://unfoldingmaps.org) - Used to create interactive maps and geovisualizations in Java

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
