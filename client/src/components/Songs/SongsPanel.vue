<template>
  <panel title="Songs">
    <v-btn
      slot="action"
      :to="{ name: 'songs-create' }"
      class="cyan accent-2"
      light
      medium
      absolute
      right
      middle
      fab>
      <v-icon>add</v-icon>
    </v-btn>

    <div 
      v-for="song in songs"
      class="song"
      :key="song.id">

      <v-layout>
        <v-flex xs6>
          <div class="song-title">
            {{ song.title }}
          </div>
          <div class="song-artist">
            {{ song.artist }}
          </div>
          <div class="song-genre">
            {{ song.genre }}
          </div>

          <v-btn
            dark
            class="cyan"
            @click="viewSong(song.id, false)">
            View
          </v-btn>
          <v-btn
            dark
            class="cyan"
            @click="viewSong(song.id, true)">
            View in new tab
          </v-btn>
          <v-btn
            dark
            class="cyan"
            @click="viewSong(song.id, false, true)">
            View in new window
          </v-btn>
        </v-flex>

        <v-flex xs6>
          <img class="album-image" :src="song.albumImageUrl" />
        </v-flex>
      </v-layout>
    </div>
  </panel>
</template>

<script>
import SongsService from '@/services/SongsService'

export default {
  data() {
    return {
      songs: null
    }
  },
  watch: {
    '$route.query.search': {
      immediate: true,
      async handler(value) {
        this.songs = (await SongsService.index(value)).data
      }
    }
  },
  methods: {
    viewSong(songId, newTab, newWindow) {
      const route = { name: 'song', params: { songId } };
      const resolvedRoute = this.$router.resolve(route).href;

      if (newTab) {
        window.open(resolvedRoute, '_blank');
      }
      if (newWindow) 
      {
        window.open(resolvedRoute, '_blank', 'menubar=no,toolbar=no,location=no,status=no');
      } else {
        this.$router.push(route);
      }
    }
  }
}
</script>

<style scoped>
.song {
  padding: 20px;
  height: 330px;
  overflow: hidden;
}

.song-title {
  font-size: 30px;
}

.song-artist {
  font-size: 24px;
}

.song-genre {
  font-size: 18px;
}

.album-image {
  width: 70%;
  margin: 0 auto;
}
</style>
