package dao;

import java.util.List;

import model.BookComment;

public interface BookCommentDao {

	public BookComment save(BookComment bookComment);

	public void delete(BookComment bookComment);

	public void update(BookComment bookComment);

	public List<BookComment> getBookCommentByIsbn(long isbn);
	
	public List<BookComment> getBookCommentById(int id);

	public List<BookComment> getAllBookComments();
	
	public List<BookComment> getBookCommentByIsbnLimited(long isbn,int maxCount);

}