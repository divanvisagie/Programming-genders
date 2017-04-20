let fs = require('fs')
let data = require('./src/main/resources/data')

const countedData = data
    .filter(x => x.tech_do)
    .map(x => {
        return {
            gender: x.gender,
            tech_do: x.tech_do.split(';').map(x => x.trim()),
            tech_want: x.tech_want.split(';')
        }
    }).reduce((acc, item) => {

        item.tech_do.forEach(tech => {
            if (!acc[tech]) {
                acc[tech] = { male: 0 , female: 0 }
            }
            if (item.gender) {
                if (item.gender.toLowerCase() == "male" || item.gender.toLowerCase() == "female") {
                    acc[tech][item.gender.toLowerCase()] += 1
                }
            }
        })

        return acc

    }, {})

    const arrData = Object.keys(countedData).map(key => {
        return {
            language: key,
            male: countedData[key].male,
            female: countedData[key].female
        }
    })

    const filteredArray = arrData.filter(x => {
        return (["Scala","C#","Java", "Rust", "Clojure", "Haskell", "JavaScript", "Python"].includes(x.language))
    })

    const csvdata = filteredArray.map(x => {
        return `${x.language},${x.male},${x.female}`
    })

    console.log(csvdata)

fs.writeFile('./src/main/resources/genderlang.csv', csvdata.join('\n'), (err) => {
   if (err) throw err
   console.log("File saved")
})