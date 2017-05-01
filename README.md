# Data for gender diversity in programming
## Based on Stack Overflow Survey data
[Original Data](http://stackoverflow.com/insights/survey)

Viewing

Go [here](http://dvisagie.com/stackoverflow-data-analysis/) to view the outcome in html format.

## Usage

```sh
./extract.sh
sbt run
```

The output is the set of chart pngs generated in the `./chart` directory


#### Editor Config

Add the following to `~/.sbt/0.13/plugins/plugins.sbt`

```scala
addSbtPlugin("org.ensime" % "sbt-ensime" % "1.12.9")
```



`sbt ensimeConfig`