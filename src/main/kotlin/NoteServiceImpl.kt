object NoteServiceImpl : NoteService<Note, Comment> {
    private var notes = mutableListOf(Note())
    private var comments = mutableListOf(Comment())
    private var noteId: Int = 1

    override fun addNote(note: Note): Note {
        notes.add(note.copy(id = noteId))
        noteId++
        return notes.last()
    }

    override fun createComment(comment: Comment, noteId: Int): Comment {
        notes.forEach { note ->
            if (comment.idOfNote == noteId) {
                comments.add(comment)
                return comments.last()
            }
        }
        throw NoteNotFoundException("Такой заметки не найдено")
    }

    override fun deleteNote(id: Int) {
        notes = notes.filter { it.id != id } as MutableList<Note>
        comments = comments.filter { it.idOfNote != id } as MutableList<Comment>
    }

    override fun deleteComment(id: Int): Boolean {
        comments = comments.filter { it.id != id } as MutableList<Comment>
        return true
    }

    override fun editNote(noteId: Int, newNote: Note): Boolean {
        for ((index, note) in notes.withIndex()) {
                if (noteId == note.id) {
                    notes[index] = note.copy(id = note.id, ownerId = note.ownerId, title = newNote.title,
                            text = newNote.text)
                    return true
                }
            }
        throw NoteNotFoundException("Заметка не найдена")
    }

    override fun editComment(id: Int, comment: Comment): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (!comment.deleted) {
                if (id == comment.id) {
                    comments[index] = comment.copy(idOfNote = comment.idOfNote, id = comment.id,
                        deleted = false, text = comment.text)
                    return true
                }
            }
        }
        throw CommentNotFoundException("Комментарий не найден")
    }

    override fun getNotes(ownerId: Int) {
        notes.forEach { note ->
            if (note.ownerId == ownerId) println(notes)
        }
    }

    override fun getNoteById(id: Int) {
        notes.forEach { note ->
            if (note.id == id) println(note.text)
        }
        throw NoteNotFoundException("Заметка не найдена")
    }

    override fun getComments(id: Int) {
        comments.forEach { comment ->
            if (comment.idOfNote == id) println(comment.text)
        }
    }

    override fun restoreComment(id: Int): Boolean {
        comments.forEach {comment ->
            if (comment.deleted == true) comment.deleted = false
            return true
        }
        throw CommentNotFoundException("Комментарий не найден")
    }

    }

