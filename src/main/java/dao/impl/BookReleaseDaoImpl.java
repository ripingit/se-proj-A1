package dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mongodb.gridfs.GridFSDBFile;

import dao.BookReleaseDao;
import model.BookRelease;
import model.Picture;

@SuppressWarnings("deprecation")
public class BookReleaseDaoImpl extends HibernateDaoSupport implements BookReleaseDao {

	public Integer save(BookRelease bookRelease) {
		return (Integer) getHibernateTemplate().save(bookRelease);
	}

	public void delete(BookRelease bookRelease) {
		getHibernateTemplate().delete(bookRelease);
	}

	public void update(BookRelease bookRelease) {
		getHibernateTemplate().merge(bookRelease);
	}

	public BookRelease getBookReleaseByR_id(int r_id) {
		@SuppressWarnings("unchecked")
		List<BookRelease> bookReleases = (List<BookRelease>) getHibernateTemplate().find(
				"from BookRelease as b where b.r_id=?", r_id);
		BookRelease bookRelease = bookReleases.size() > 0 ? bookReleases.get(0) : null;
		return bookRelease;
	}
	
	public List<BookRelease> getBookReleaseById(int id) {
		@SuppressWarnings("unchecked")
		List<BookRelease> bookReleases = (List<BookRelease>) getHibernateTemplate().find(
				"from BookRelease as b where b.id=?", id);
		return bookReleases;
	}
	
	public List<BookRelease> getBookReleaseByIsbn(int isbn) {
		@SuppressWarnings("unchecked")
		List<BookRelease> bookReleases = (List<BookRelease>) getHibernateTemplate().find(
				"from BookRelease as d where d.isbn=?", isbn);
		return bookReleases;
	}
	
	public List<BookRelease> getBookReleaseByStatus(int status) {
		@SuppressWarnings("unchecked")
		List<BookRelease> bookReleases = (List<BookRelease>) getHibernateTemplate().find(
				"from BookRelease as s where s.status=?", status);
		return bookReleases;
	}

	public List<BookRelease> getAllBookReleases() {
		@SuppressWarnings("unchecked")
		List<BookRelease> bookReleases = (List<BookRelease>) getHibernateTemplate()
				.find("from BookRelease");
		return bookReleases;
	}
	
	private GridFsTemplate GridFsTemplate;

	public void setGridFsTemplate(GridFsTemplate GridFsTemplate) {
	    this.GridFsTemplate = GridFsTemplate;
	}
	
	public Picture getPictureByR_id(int r_id) {
		GridFSDBFile result = GridFsTemplate.findOne(new Query(Criteria.where("metadata.r_id").is(r_id)));
		Picture picture =new Picture(result.getContentType(),result.getInputStream());
		return picture;
	}
	
}