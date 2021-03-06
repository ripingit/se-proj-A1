package dao;

import java.util.List;

import model.BookCateRelationship;

public interface BookCateRelationshipDao {

	public BookCateRelationship save(BookCateRelationship bookCateRelationship);

	public void delete(BookCateRelationship bookCateRelationship);

	public void update(BookCateRelationship bookCateRelationship);

	public List<Integer> getCategoriesIdByIsbn(long isbn);
	
	public List<Long> getBooksIsbnByCate_id(int cate_id);
	
	public List<Long> getBooksIsbnByCate_idLimits(int cate_id, int offset, int maxCount);

	public int countBooksInCate(int cate_id);
	
	public List<BookCateRelationship> getAllBookCateRelationships();

}