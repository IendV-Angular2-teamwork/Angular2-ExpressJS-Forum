const express = require('express')
const authCheck = require('../middleware/auth-check')
const flowersData = require('../data/flowers')

const router = new express.Router()

function validateFlowerForm (payload) {
  const errors = {}
  let isFormValid = true
  let message = ''

  payload.price = parseInt(payload.price)
  

  if (!payload || typeof payload.name !== 'string' || payload.name.length < 3) {
    isFormValid = false
    errors.name = 'Name must be more than 3 symbols.'
  }

  if (!payload || typeof payload.category !== 'string' || payload.category.length < 6) {
    isFormValid = false
    errors.category = 'Category must be more than 6 symbols.'
  }

  if (!payload || typeof payload.blossom !== 'string' || payload.blossom.length < 6) {
    isFormValid = false
    errors.category = 'Blossom must be more than 6 symbols.'
  }

  if (!payload || !payload.price || payload.price < 0) {
    isFormValid = false
    errors.price = 'Price must be a positive number.'
  }

  if (!payload || typeof payload.image !== 'string' || payload.image === 0) {
    isFormValid = false
    errors.image = 'Image URL is required.'
  }

  if (!isFormValid) {
    message = 'Check the form for errors.'
  }

  return {
    success: isFormValid,
    message,
    errors
  }
}

router.post('/create', authCheck, (req, res) => {
  const flower = req.body
  flower.createdBy = req.user.email

  const validationResult = validateFlowerForm(flower)
  if (!validationResult.success) {
    return res.status(200).json({
      success: false,
      message: validationResult.message,
      errors: validationResult.errors
    })
  }

  flowersData.save(flower)

  res.status(200).json({
    success: true,
    message: 'Flower added successfuly.',
    flower
  })
})

router.get('/all', (req, res) => {
  const page = parseInt(req.query.page) || 1
  const search = req.query.search

  const flowers = flowersData.all(page, search)

  res.status(200).json(flowers)
})

router.get('/details/:id', authCheck, (req, res) => {
  const id = req.params.id

  const flower = flowersData.findById(id)

  if (!flower) {
    return res.status(200).json({
      success: false,
      message: 'Flower does not exists!'
    })
  }

  let response = {
    id,
    name: flower.name,
    category: flower.category,
    blossom: flower.blossom,
    price: flower.price,
    image: flower.image,
    createdOn: flower.createdOn,
    likes: flower.likes.length
  }  

  res.status(200).json(response)
})

router.post('/details/:id/reviews/create', authCheck, (req, res) => {
  const id = req.params.id
  const user = req.user.name

  const flower = flowersData.findById(id)

  if (!flower) {
    return res.status(200).json({
      success: false,
      message: 'Flower does not exists!'
    })
  }

  let rating = req.body.rating
  const comment = req.body.comment

  if (rating) {
    rating = parseInt(rating)
  }

  if (!rating || rating < 1 || rating > 5) {
    return res.status(200).json({
      success: false,
      message: 'Rating must be between 1 and 5.'
    })
  }

  flowersData.addReview(id, rating, comment, user)

  res.status(200).json({
    success: true,
    message: 'Review added successfuly.',
    review: {
      id,
      rating,
      comment,
      user
    }
  })
})

router.post('/details/:id/like', authCheck, (req, res) => {
  const id = req.params.id
  const user = req.user.email

  const flower = flowersData.findById(id)

  if (!flower) {
    return res.status(200).json({
      success: false,
      message: 'flower does not exists!'
    })
  }

  const result = flowersData.like(id, user)

  if (!result) {
    return res.status(200).json({
      success: false,
      message: 'This user has already liked this flower!'
    })
  }

  return res.status(200).json({
    success: true,
    message: 'Thank you for your like!'
  })
})

router.get('/details/:id/reviews', authCheck, (req, res) => {
  const id = req.params.id

  const flower = flowersData.findById(id)

  if (!flower) {
    return res.status(200).json({
      success: false,
      message: 'Flower does not exists!'
    })
  }

  const response = flowersData.allReviews(id)

  res.status(200).json(response)
})

router.get('/mine', authCheck, (req, res) => {
  const user = req.user.email

  const flowers = flowersData.byUser(user)

  res.status(200).json(flowers)
})

router.post('/delete/:id', authCheck, (req, res) => {
  const id = req.params.id
  const user = req.user.email

  const flower = flowersData.findById(id)

  if (!flower || flower.createdBy !== user) {
    return res.status(200).json({
      success: false,
      message: 'Flower does not exists!'
    })
  }

  flowersData.delete(id)

  return res.status(200).json({
    success: true,
    message: 'Flower deleted successfully!'
  })
})

module.exports = router
