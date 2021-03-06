package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.BookCommentDao;
import model.BookComment;

@SuppressWarnings("deprecation")
public class BookCommentDaoImpl extends HibernateDaoSupport implements BookCommentDao {

	public BookComment save(BookComment bookComment) {
		return (BookComment) getHibernateTemplate().save(bookComment);
	}

	public void delete(BookComment bookComment) {
		getHibernateTemplate().delete(bookComment);
	}

	public void update(BookComment bookComment) {
		getHibernateTemplate().merge(bookComment);
	}

	public List<BookComment> getBookCommentById(int id) {
		@SuppressWarnings("unchecked")
		List<BookComment> bookComments = (List<BookComment>) getHibernateTemplate().find(
				"from BookComment as t where t.id=?", id);
		return bookComments;
	}
	
	public List<BookComment> getBookCommentByIsbn(long isbn) {
		Query q = getSession().createQuery("from BookComment as t where t.isbn=:isbn");
		q.setLong("isbn", isbn);
		@SuppressWarnings("unchecked")
		List<BookComment> bookComments = (List<BookComment>) q.list();
		return bookComments;

	}
	
	public List<BookComment> getAllBookComments() {
		@SuppressWarnings("unchecked")
		List<BookComment> bookComments = (List<BookComment>) getHibernateTemplate()
				.find("from BookComment");
		return bookComments;
	}
	
	public List<BookComment> getBookCommentByIsbnLimited(long isbn,int maxCount){
		Query q = getSession().createQuery("from BookComment as t  where t.isbn=:isbn");
		q.setLong("isbn", isbn);
		q.setMaxResults(maxCount);
		@SuppressWarnings("unchecked")
		List<BookComment> bookcomments = (List<BookComment>)q.list();
		return bookcomments;
	}
	
}