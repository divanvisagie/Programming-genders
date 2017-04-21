let fs = require('fs')
let data = require('./data/rawdata')

const countedData = data
    .filter(x => x.tech_do)
    .map(x => {
        return {
            gender: x.gender
        }
    })