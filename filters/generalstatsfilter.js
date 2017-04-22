let fs = require('fs')
let data = require('../data/rawdata')

var filteredData = 
    data
    .filter(x => x.gender)
    .filter(x => x.tech_do)
    .filter(x => x.tech_want)

const headers = Object.keys(filteredData[0])

const csvHeader = headers.join(',')
const csvBody = filteredData.map(x => {
    return Object.values(x).join(',')
}).join('/n')


var csvFileContent = [csvHeader, csvBody].join('/n');

//console.log(csvFileContent)

console.log(csvHeader)

fs.writeFile('./data/clean.csv', csvFileContent , err => {
    if (err) throw err
    console.log('Wrote File')
})
