const Thread = require('../data/Thread')

module.exports = {
  index: (req, res) => {
    res.header("Access-Control-Allow-Origin", "*");
    Thread.find().populate('author').sort('-lastAnswerDate').limit(20).then(threads => {
      res.json({
        success: true,
        threads: threads
      })  
      //res.render('home/index', {
      //  threads: threads
      //})
    })
  }
}
