let fs = require('fs')
let data = require('./data/rawdata')

const countedData = data
    .filter(x => x.tech_do)
    .map(x => {
        return {
            gender: x.gender,
            tech_do: x.tech_do.split(';').map(x => x.trim()),
            tech_want: x.tech_want.split(';')
        }
    }).reduce((acc, item) => {


        ["tech_do", "tech_want"].forEach(columnName => {
            item[columnName].forEach(tech => {
                if (!acc[tech]) {
                    acc[tech] = { male: 0 , female: 0, other: 0, undisclosed: 0 }
                }
                if (item.gender) {
                    if (item.gender.toLowerCase() === "male" || item.gender.toLowerCase() === "female") {
                        acc[tech][item.gender.toLowerCase()] += 1
                    } else if (item.gender.toLowerCase() === "other") {
                        acc[tech].other += 1
                    } else {
                        acc[tech].undisclosed += 1
                    }
                }
            })
        })


        return acc

    }, {})

const arrData = Object.keys(countedData).map(key => {
    return {
        language: key,
        male: countedData[key].male,
        female: countedData[key].female,
        other: countedData[key].other,
        undisclosed: countedData[key].undisclosed
    }
})

const filteredArray = arrData.filter(x => {
    return (["Go","Scala","C#","Java", "Rust", "CSS", "C++", "JavaScript", "Python"].includes(x.language))
})

const csvdata = filteredArray.map(x => {
    return `${x.language},${x.male},${x.female},${x.other},${x.undisclosed}`
})

console.log(csvdata)

fs.writeFile('./data/genderlang.csv', csvdata.join('\n'), (err) => {
   if (err) throw err
   console.log("File saved")
})