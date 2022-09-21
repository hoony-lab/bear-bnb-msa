
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import RoomManager from "./components/listers/RoomCards"
import RoomDetail from "./components/listers/RoomDetail"
import ReviewManager from "./components/listers/ReviewCards"
import ReviewDetail from "./components/listers/ReviewDetail"

import ReservationManager from "./components/listers/ReservationCards"
import ReservationDetail from "./components/listers/ReservationDetail"

import PaymentManager from "./components/listers/PaymentCards"
import PaymentDetail from "./components/listers/PaymentDetail"

import MessageManager from "./components/listers/MessageCards"
import MessageDetail from "./components/listers/MessageDetail"


import RoomViewView from "./components/RoomViewView"
import RoomViewViewDetail from "./components/RoomViewViewDetail"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/rooms',
                name: 'RoomManager',
                component: RoomManager
            },
            {
                path: '/rooms/:id',
                name: 'RoomDetail',
                component: RoomDetail
            },
            {
                path: '/reviews',
                name: 'ReviewManager',
                component: ReviewManager
            },
            {
                path: '/reviews/:id',
                name: 'ReviewDetail',
                component: ReviewDetail
            },

            {
                path: '/reservations',
                name: 'ReservationManager',
                component: ReservationManager
            },
            {
                path: '/reservations/:id',
                name: 'ReservationDetail',
                component: ReservationDetail
            },

            {
                path: '/payments',
                name: 'PaymentManager',
                component: PaymentManager
            },
            {
                path: '/payments/:id',
                name: 'PaymentDetail',
                component: PaymentDetail
            },

            {
                path: '/messages',
                name: 'MessageManager',
                component: MessageManager
            },
            {
                path: '/messages/:id',
                name: 'MessageDetail',
                component: MessageDetail
            },


            {
                path: '/roomViews',
                name: 'RoomViewView',
                component: RoomViewView
            },
            {
                path: '/roomViews/:id',
                name: 'RoomViewViewDetail',
                component: RoomViewViewDetail
            },


    ]
})
