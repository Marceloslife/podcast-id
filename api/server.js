const express = require('express');
const mongoose = require('mongoose');

const app = express();

// Mengatur koneksi ke MongoDB Atlas
mongoose.connect('mongodb+srv://marcelo123:marcelo123@cluster0.rcoboug.mongodb.net/podcast?retryWrites=true&w=majority', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

// Membuat skema untuk koleksi Podcast
const podcastSchema = new mongoose.Schema({
  name: String,
  description: String,
});

// Membuat model Podcast
const Podcast = mongoose.model('Podcast', podcastSchema);

// Middleware untuk parsing body dari request
app.use(express.json());

// Mendapatkan semua podcast
app.get('/podcasts', async (req, res) => {
  try {
    const podcasts = await Podcast.find();
    res.json(podcasts);
  } catch (error) {
    res.status(500).json({ error: 'Internal server error' });
  }
});

// Mendapatkan podcast berdasarkan ID
app.get('/podcasts/:id', async (req, res) => {
  try {
    const podcast = await Podcast.findById(req.params.id);
    if (podcast) {
      res.json(podcast);
    } else {
      res.status(404).json({ error: 'Podcast not found' });
    }
  } catch (error) {
    res.status(500).json({ error: 'Internal server error' });
  }
});

// Menambahkan podcast baru
app.post('/podcasts', async (req, res) => {
  try {
    const newPodcast = await Podcast.create({
      name: req.body.name,
      description: req.body.description,
    });
    res.json(newPodcast);
  } catch (error) {
    res.status(500).json({ error: 'Internal server error' });
  }
});

// Menghapus podcast berdasarkan ID
app.delete('/podcasts/:id', async (req, res) => {
  try {
    const deletedPodcast = await Podcast.findByIdAndDelete(req.params.id);
    if (deletedPodcast) {
      res.json({ message: 'Podcast deleted' });
    } else {
      res.status(404).json({ error: 'Podcast not found' });
    }
  } catch (error) {
    res.status(500).json({ error: 'Internal server error' });
  }
});

// Menjalankan server
app.listen(3000, () => {
  console.log('Server started on port 3000');
});
