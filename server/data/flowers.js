const flowers = {}
let currentId = 0

module.exports = {
  total: () => Object.keys(flowers).length,
  save: (flower) => {
    const id = ++currentId
    flower.id = id

    let newFlower = {
      id,
      name: flower.name,
      category: flower.category,
      blossom: flower.blossom,
      price: flower.price,
      image: flower.image,
      createdOn: new Date(),
      createdBy: flower.createdBy,
      likes: [],
      reviews: []
    }    

    flowers[id] = newFlower
  },
  all: (page, search) => {
    const pageSize = 10

    let startIndex = (page - 1) * pageSize
    let endIndex = startIndex + pageSize

    return Object
      .keys(flowers)
      .map(key => flowers[key])
      .filter(flower => {
        if (!search) {
          return true
        }

        const flowerName = flower.name.toLowerCase()
        const flowerCategory = flower.category.toLowerCase()
        const searchTerm = search.toLowerCase()

        return flowerCategory.indexOf(searchTerm) >= 0 ||
          flowerName.indexOf(searchTerm) >= 0
      })
      .sort((a, b) => b.id - a.id)
      .slice(startIndex, endIndex)
  },
  findById: (id) => {
    return flowers[id]
  },
  addReview: (id, rating, comment, user) => {
    const review = {
      rating,
      comment,
      user,
      createdOn: new Date()
    }

    flowers[id].reviews.push(review)
  },
  allReviews: (id) => {
    return flowers[id]
      .reviews
      .sort((a, b) => b.createdOn - a.createdOn)
      .slice(0)
  },
  like: (id, user) => {
    const likes = flowers[id].likes

    if (likes.indexOf(user) >= 0) {
      return false
    }

    likes.push(user)

    return true
  },
  byUser: (user) => {
    return Object
      .keys(flowers)
      .map(key => flowers[key])
      .filter(flower => flower.createdBy === user)
      .sort((a, b) => b.id - a.id)
  },
  delete: (id) => {
    delete flowers[id]
  }
}
