object CommentServiceImpl : NoteAndCommentService<Comment> {
    private var comments = mutableListOf(Comment())

    override fun add(comment: Comment): Comment {
        comments.add(comment)
        return comments.last()
    }

    override fun delete(id: Int) {
        comments.forEach { comment ->
            if (id == comment.id) comment.deleted = true
        }
    }

    override fun edit(id: Int, newComment: Comment): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (!comment.deleted) {
                if (id == comment.id) {
                    comments[index] = comment.copy(
                        idOfNote = comment.idOfNote, id = comment.id,
                        deleted = false, text = comment.text
                    )
                    return true
                }
            }
        }
        throw CommentNotFoundException("Комментарий не найден")
    }

    override fun read(noteId: Int): List<Comment> {
        comments.forEach { comment ->
            if (comment.idOfNote == noteId) return comments
        }
        throw CommentNotFoundException("Комментарий не найден")
    }

    override fun getById(id: Int): Comment {
        comments.forEach { comment ->
            if (comment.id == id) return comment
        }
        throw CommentNotFoundException("Комментарий не найден")
    }

    override fun restore(id: Int) {
        comments.forEach { comment ->
            if (comment.id == id && comment.deleted == true) comment.deleted = false
        }
        throw CommentNotFoundException("Комментарий не найден")
    }
}
