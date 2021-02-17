interface NoteService<N, C> {

    fun addNote(n: N): N

    fun createComment(c: C, i: Int): C

    fun deleteNote(i: Int)

    fun deleteComment(i: Int): Boolean

    fun editNote(i: Int, n: N): Boolean

    fun editComment(i: Int, c: C): Boolean

    fun getNotes(i: Int)

    fun getNoteById(i: Int)

    fun getComments(i: Int)

    fun restoreComment(i: Int): Boolean
}