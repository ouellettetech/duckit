package com.ouellettetech.duckit.networking

class APIEndPoints {
    companion object {
        const val SIGN_IN = "/signin"
        const val SIGN_UP = "/signup"
        const val POSTS = "/posts"
        const val UP_VOTE = "/posts/{id}/upvote"
        const val DOWN_VOTE = "/posts/:id/downvote"
        const val POST = "/posts"
    }
}