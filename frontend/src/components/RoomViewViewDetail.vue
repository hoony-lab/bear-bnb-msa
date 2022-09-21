<template>

    <v-card outlined>
        <v-card-title>
            RoomView # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="RoomStatus" v-model="item.roomStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="ReviewCnt" v-model="item.reviewCnt" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="ReservationStatus" v-model="item.reservationStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="PayStatuss" v-model="item.payStatuss" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'RoomViewViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/roomViews/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

