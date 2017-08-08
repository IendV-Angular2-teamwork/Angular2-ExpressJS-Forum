const express = require('express')
const cookieParser = require('cookie-parser')
const bodyParser = require('body-parser')
const controllers = require('../controllers')
const session = require('express-session')
const passport = require('passport')
const handlebars = require('express-handlebars')
const helpers = require('./helpers')
const cors = require('cors')
const localSignupStrategy = controllers.users.signupStrategy
const localLoginStrategy = controllers.users.loginStrategy


module.exports = (app) => {
  app.engine('handlebars', handlebars({
    helpers: helpers,
    defaultLayout: 'main'
  }))

  app.use(cors())
  passport.use('local-signup', localSignupStrategy)
  passport.use('local-login', localLoginStrategy)

  app.set('view engine', 'handlebars')
  app.use(cookieParser())
  app.use(bodyParser.urlencoded({ extended: true }))
  app.use(session({
    secret: 'neshto-taino!@#$%',
    resave: false,
    saveUninitialized: false
  }))
  app.use(passport.initialize())
  app.use(passport.session())

  app.use((req, res, next) => {
    if (req.user) {
      res.locals.currentUser = req.user
      res.locals.isAdmin = req.user.roles.indexOf('Admin') > -1
      res.locals.isActive = !req.user.isBlocked
    }

    next()
  })

  app.use(express.static('public'))

  console.log('Express ready!')
}
