const express = require('express')
const flowersData = require('../data/flowers')
const usersData = require('../data/users')

const router = new express.Router()

router.get('/', (req, res) => {
  const flowers = flowersData.total()
  const users = usersData.total()

  res.status(200).json({
    flowers,
    users
  })
})

module.exports = router
