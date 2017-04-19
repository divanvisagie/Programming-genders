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

    //console.log(countedData)

    const arrData = Object.keys(countedData).map(key => {
        return {
            language: key,
            male: countedData[key].male,
            female: countedData[key].female
        }
    })

   // console.log(arrData)

    const filteredArray = arrData.filter(x => {
        return (["Scala","C#","Java"].includes(x.language))
    })

    console.log(filteredArray)

    const csvdata = filteredArray.map(x => {
        return `${x.language},${x.male},${x.female}`
    })

    console.log(csvdata)

//    .map(x => `${x.gender},${x.tech_do},${x.tech_want}`)
//
fs.writeFile('./src/main/resources/genderlang.csv', csvdata.join('\n'), (err) => {
   if (err) throw err
   console.log("File saved")
})

//fs.writeFile('message.txt', 'Hello Node.js', (err) => {
//  if (err) throw err;
//  console.lo('The file has been saved!');
//});