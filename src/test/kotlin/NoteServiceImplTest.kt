import org.junit.Test
import org.junit.Assert.*

class NoteServiceImplTest {

    @Test
    fun addNote() {
        val note1 = Note(id = 55)
        val expected = 1
        val result = NoteServiceImpl.addNote(note1).id

        assertEquals(expected, result)
    }

    @Test
    fun createComment() {
        val comment1 = Comment(id = 3, idOfNote = 1)
        val expected = 3
        val result = NoteServiceImpl.createComment(comment1, 1).id

        assertEquals(expected, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentNoNote() {
        val comment2 = Comment(id = 4, idOfNote = 5)
        NoteServiceImpl.createComment(comment2, 2)
    }

    @Test
    fun deleteComment() {
        val expected = true
        val result = NoteServiceImpl.deleteComment(3)

        assertEquals(expected, result)
    }

    @Test
    fun editNote() {
        val note1Corrected = Note(id = 56, title = "newTitle")
        NoteServiceImpl.addNote(note1Corrected)
        val expected = true
        val result = NoteServiceImpl.editNote(1, note1Corrected)

        assertEquals(expected, result)
    }
    @Test(expected = NoteNotFoundException::class)
    fun editNoteNoNote() {
        val note1Corrected = Note(id = 56, title = "newTitle")
        NoteServiceImpl.addNote(note1Corrected)

       NoteServiceImpl.editNote(5, note1Corrected)
    }

    @Test
    fun editComment() {
        val comment3 = Comment(id = 5, idOfNote = 1)
        val commentEdit = Comment(id = 6, idOfNote = 1, text = "newText")
        NoteServiceImpl.createComment(comment3, 1)
        val expected = true
        val result = NoteServiceImpl.editComment(5, commentEdit)

        assertEquals(expected, result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentNoComment() {
        val commentEdit = Comment(id = 6, idOfNote = 1, text = "newText")
        NoteServiceImpl.createComment(commentEdit, 1)
        NoteServiceImpl.editComment(7, commentEdit)
    }

    @Test
    fun restoreComment() {
        val expected = true
        val result = NoteServiceImpl.restoreComment(3)

        assertEquals(expected, result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentNoComment() {
        val commentEdit = Comment(id = 6, idOfNote = 1, text = "newText")
        NoteServiceImpl.createComment(commentEdit, 1)
        NoteServiceImpl.editComment(8, commentEdit)
    }
}


//    @Test(expected = CommentDeleteNotFoundException::class)
//    fun testRestoreCommentShouldThrow() {
//        //Arrange
//        val commentTest: Comment = Comment(1, "", 1, true)
//        commentTest.commentId = 1
//        commentTest.deleted = true
//        val expected = CommentDeleteNotFoundException::class
//        //Act
//        restoreComment(10)
//        val result = CommentDeleteNotFoundException::class
//        //Assert
//        assertEquals(expected, result)
//    }
//}