# Data for gender diversity in programming
## Based on Stack Overflow Survey data
[Original Data](http://stackoverflow.com/insights/survey)

This is my attempt at an analysis of the situation of gender diversity in the programming community

## Language Gender Distribution
![Chart](./charts/languageGenderDistribution.png)

## Usage

```sh
yarn
yarn build
sbt run
```


#### Editor Config

Add the following to `~/.sbt/0.13/plugins/plugins.sbt`

```scala
addSbtPlugin("org.ensime" % "sbt-ensime" % "1.12.9")
```



`sbt ensimeConfig`