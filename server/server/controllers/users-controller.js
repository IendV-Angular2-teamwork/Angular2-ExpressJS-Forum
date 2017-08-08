const encryption = require('../utilities/encryption')
const passport = require('passport')
const localSignupStrategy = require('../passport/local-signup')
const localLoginStrategy = require('../passport/local-login')
const User = require('mongoose').model('User')
const Thread = require('../data/Thread')
const Answer = require('../data/Answer')

module.exports = {
  signupStrategy: localSignupStrategy,
  loginStrategy: localLoginStrategy,
  registerGet: (req, res) => {
    res.render('users/register')
  },
  registerPost: (req, res) => {
    let reqUser = req.body
    // Add validations!

    let salt = encryption.generateSalt()
    let hashedPassword = encryption.generateHashedPassword(salt, reqUser.password)

    User.create({
      username: reqUser.username,
      firstName: reqUser.firstName,
      lastName: reqUser.lastName,
      salt: salt,
      hashedPass: hashedPassword
    }).then(user => {
      req.logIn(user, (err, user) => {
        if (err) {
          res.locals.globalError = err
          res.render('users/register', user)
        }

        res.redirect('/')
      })
    })
  },
  loginGet: (req, res, next) => {
    return passport.authenticate('local-login', (err, token, userData) => {
      if (err) {
        if (err.firstName === 'IncorrectCredentialsError') {
          return res.status(200).json({
            success: false,
            message: err.message
          })
        }

        return res.status(200).json({
          success: false,
          message: 'Could not process the form.'
        })
      }
      return res.json({
        success: true,
        message: 'You have successfully logged in!',
        token,
        user: userData
      })
    })(req, res, next)
  },
  loginPost: (req, res, next) => {
    return passport.authenticate('local-login', (err, token, userData) => {
      if (err) {
        if (err.firstName === 'IncorrectCredentialsError') {
          return res.status(200).json({
            success: false,
            message: err.message
          })
        }

        return res.status(200).json({
          success: false,
          message: 'Could not process the form.'
        })
      }
      return res.json({
        success: true,
        message: 'You have successfully logged in!',
        token,
        user: userData
      })
    })(req, res, next)
  },
  logout: (req, res) => {
    req.logout()
    res.redirect('/')
  },

  addAdminGet: (req, res) => {
    User.find({roles: {$ne: 'Admin'}}).then(users => {
      res.render('users/admin-add', {
        users: users
      })
    })
  },

  addAdminPost: (req, res) => {
    let userId = req.body.user

    User.findByIdAndUpdate(userId, {$addToSet: {roles: 'Admin'}}).then(() => {
      res.redirect('/admins/all')
    })
  },

  all: (req, res) => {
    User.find({roles: {$in: ['Admin']}}).then(admins => {
      res.render('users/admin-all', {
        admins: admins
      })
    })
  },

  profile: (req, res) => {
    let username = req.params.username

    User.findOne({username: username}).then(user => {
      Thread.find({author: user._id}).then(threads => {
        Answer.find({author: user._id}).populate('thread').then(answers => {
          res.render('users/profile', {
            user: user,
            threads: threads,
            answers: answers
          })
        })
      })
    })
  },

  block: (req, res) => {
    let id = req.params.id

    User.findByIdAndUpdate(id, {$set: {isBlocked: true}}).then(user => {
      res.redirect(`/profile/${user.username}`)
    })
  },

  unblock: (req, res) => {
    let id = req.params.id

    User.findByIdAndUpdate(id, {$set: {isBlocked: false}}).then(user => {
      res.redirect(`/profile/${user.username}`)
    })
  }
}
