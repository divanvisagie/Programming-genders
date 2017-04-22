let fs = require('fs')
let data = require('../data/rawdata')

const countedData = data
    .filter(x => x.desktop_os)
    .map(x => {
        return {
            gender: x.gender,
            desktop_os: x.desktop_os
        }
    }).reduce((acc, item) => {
        if (!acc[item.desktop_os]) {
            acc[item.desktop_os] = {
                male: 0,
                female: 0,
                other: 0,
                undisclosed: 0
            }
        }
        if (item.gender) {
            if (item.gender.toLowerCase() === "male" || item.gender.toLowerCase() === "female") {
                acc[item.desktop_os][item.gender.toLowerCase()] += 1
            } else if (item.gender.toLowerCase() === "other") {
                acc[item.desktop_os].other += 1
            } else {
                acc[item.desktop_os].undisclosed += 1
            }
        }

        return acc

    }, {})


const normalized = Object.keys(countedData).map(key => {

    return {
        os: key,
        male: countedData[key].male,
        female: countedData[key].female,
        other: countedData[key].other,
        undisclosed: countedData[key].undisclosed
    }
})
const csvdata = normalized.map(x => {
    return `${x.os},${x.male},${x.female},${x.other},${x.undisclosed}`
})

console.log(csvdata)

fs.writeFile('./data/genderos.csv', csvdata.join('\n'), (err) => {
    if (err) throw err
    console.log("File saved")
})