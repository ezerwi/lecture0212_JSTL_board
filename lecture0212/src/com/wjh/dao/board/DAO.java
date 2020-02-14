package com.wjh.dao.board;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wjh.model.Model;

public class DAO {

	public DAO() {

	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://localhost:3306/board0212";
	private final String ID = "root";
	private final String PW = "1234";

	private void connDB() {

		try {
			Class.forName(this.JDBC_DRIVER);
			this.conn = DriverManager.getConnection(this.DB_URL, this.ID, this.PW);
		} catch (ClassNotFoundException e) {
			System.out.println("ERR_connDB()_Driver Load__");
		} catch (SQLException e) {
			System.out.println("ERR_connDB()_DB_Connection_");
		}
	} // connDB()

	// +----------+--------------+------+-----+---------+----------------+
	// | Field | Type | Null | Key | Default | Extra |
	// +----------+--------------+------+-----+---------+----------------+
	// | idx | int(11) | NO | PRI | NULL | auto_increment |
	// | num | int(11) | YES | | NULL | |
	// | subject | varchar(100) | NO | | NULL | |
	// | writer | varchar(50) | NO | | NULL | |
	// | contents | text | NO | | NULL | |
	// | ip | varchar(15) | YES | | NULL | |
	// | hit | int(11) | YES | | NULL | |
	// | reg_date | datetime | YES | | NULL | |
	// | mod_date | datetime | YES | | NULL | |
	// +----------+--------------+------+-----+---------+----------------+

	public List<Model> select_list(Model m) {
		List<Model> m_list = null;

		int pageNum = Integer.parseInt(m.get_pageNum());
		int list_count = m.get_list_count();
		String searchType = m.get_searchType();
		String searchText = m.get_searchText();

//		 System.out.println("_DAO_searchText:" + searchText + "___");
//		 System.out.println("_DAO_searchType:" + searchType + "___");

		String q = "select idx, subject, writer, contents, hit, reg_date from board";
		String q_where = "";

		try {

			if (!searchText.equals("")) {

				if (searchType.equals("ALL")) {
					q_where = " WHERE SUBJECT LIKE CONCAT('%',?,'%') OR WRITER LIKE CONCAT('%',?,'%') OR CONTENTS LIKE CONCAT('%',?,'%')";
				} else if (searchType.equals("SUBJECT")) {
					q_where = " WHERE SUBJECT LIKE CONCAT('%',?,'%') ";
				} else if (searchType.equals("WRITER")) {
					q_where = " WHERE WRITER LIKE CONCAT('%',?,'%') ";
				} else if (searchType.equals("CONTENTS")) {
					q_where = " WHERE CONTENTS LIKE CONCAT('%',?,'%') ";
				}
			}

			String q_order = " ORDER BY idx DESC LIMIT ?, ?";

			this.connDB();
			String query = q + q_where + q_order;
			// System.out.println("select_list_QUERY_"+query);

			this.pstmt = this.conn.prepareStatement(query);

			// 검색 q 있는 경우
			if (!q_where.equals("")) {
				if (searchType.equals("ALL")) {
					// System.out.println("search_ALL_______");
					this.pstmt.setString(1, searchText);
					this.pstmt.setString(2, searchText);
					this.pstmt.setString(3, searchText);
					this.pstmt.setInt(4, (pageNum - 1) * list_count);
					this.pstmt.setInt(5, list_count);

				} else {
					// System.out.println("search_condition_"+searchType+"______");
					this.pstmt.setString(1, searchText);
					this.pstmt.setInt(2, (pageNum - 1) * list_count);
					this.pstmt.setInt(3, list_count);

				}
			}
			// 검색 q 없는 경우
			else {
				// System.out.println("NOsearch________");
				this.pstmt.setInt(1, (pageNum - 1) * list_count);
				this.pstmt.setInt(2, list_count);

			}

			this.rs = this.pstmt.executeQuery();
			m_list = new ArrayList<Model>();

			while (this.rs.next()) {
				Model ms = new Model();
				ms.set_idx(rs.getInt("idx"));
				ms.set_subject(rs.getString("subject"));
				ms.set_writer(rs.getString("writer"));
				ms.set_contents(rs.getString("contents"));
				ms.set_hit(rs.getInt("hit"));
				ms.set_reg_date(rs.getString("reg_date"));

				m_list.add(ms);
			}
		} catch (SQLException e) {
			System.out.println("ERR_select_list()__");
		} finally {
			this.close(rs, null, conn);
		}
		return m_list;
	} // select_list ()

	public int select_count(Model m) {

		int count = 0;

		String searchType = m.get_searchType();
		String searchText = m.get_searchText();
		String q = "SELECT COUNT(idx) AS TOTAL FROM BOARD ";
		// 'AS' DB에서 가장 많이 쓰이는 것 중 하나 !!

		String q_where = "";

		try {

			// 검색어 query
			// 검색어 있는 경우에만
			if (!searchText.equals("")) {

				if (searchType.equals("ALL")) {
					q_where = " WHERE SUBJECT LIKE CONCAT('%',?,'%') OR WRITER LIKE CONCAT('%',?,'%') OR CONTENTS LIKE CONCAT('%',?,'%') ";
				} else if (searchType.equals("SUBJECT")) {
					q_where = " WHERE SUBJECT LIKE CONCAT('%',?,'%') ";
				} else if (searchType.equals("WRITER")) {
					q_where = " WHERE WRITER LIKE CONCAT('%',?,'%') ";
				} else if (searchType.equals("CONTENTS")) {
					q_where = " WHERE CONTENTS LIKE CONCAT('%',?,'%') ";
				}
			} // if(if())

			this.connDB();

			this.pstmt = this.conn.prepareStatement(q + q_where);

			// 검색어 있는 경우 각각에 따른 query
			if (!q_where.equals("")) {
				// 전체조건검색
				if (searchType.equals("ALL")) {
					this.pstmt.setString(1, searchText);
					this.pstmt.setString(2, searchText);
					this.pstmt.setString(3, searchText);
				}
				// 일부조건검색
				else
					this.pstmt.setString(1, searchText);
			}

			this.rs = pstmt.executeQuery();
			if (this.rs.next()) {
				count = this.rs.getInt("TOTAL");
			}
			// System.out.println(count);

		} catch (SQLException e) {
			System.out.println("ERR_select_count()__" + e.getMessage());
		} finally {
			this.close(this.rs, this.pstmt, this.conn);
		}
		return count;
	} // select_count()

	public Model select_one(Model m) {

		String searchType = m.get_searchText();
		String searchText = m.get_searchText();
		int idx = m.get_idx();

		String q = "SELECT * FROM BOARD WHERE IDX = ?";

		this.connDB();

		try {
			this.pstmt = this.conn.prepareStatement(q);
			this.pstmt.setInt(1, idx);
			
			this.rs = this.pstmt.executeQuery();
			while (this.rs.next()) {
				m.set_subject(this.rs.getString("subject"));
				m.set_writer(this.rs.getString("writer"));
				m.set_contents(this.rs.getString("contents"));
				m.set_reg_date(this.rs.getString("reg_date"));
				m.set_mod_date(this.rs.getString("mod_date"));
				m.set_hit(this.rs.getInt("hit"));
			}
		} catch (SQLException e) {
			System.out.println("ERR_select_one()__");
		} finally {
			this.close(this.rs, this.pstmt, this.conn);
		}
		return m;
	} // select_one ()

	public boolean insert(Model m) {

		boolean status = false;
		
		String subject = m.get_subject();
		String writer = m.get_writer();
		String contents = m.get_contents();
		String ip = m.get_ip();

		String q = "insert into board (subject, writer, contents, reg_date, mod_date, hit, ip) values (?, ?, ?, now(), now(), 0, ?) ";

		this.connDB();
		try {
			this.pstmt = this.conn.prepareStatement(q);
			this.pstmt.setString(1, subject);
			this.pstmt.setString(2, writer);
			this.pstmt.setString(3, contents);
			this.pstmt.setString(4, ip);
			int n = this.pstmt.executeUpdate();

			status = (n > 0) ? true : false;
		} catch (SQLException e) {
			System.out.println("ERR_insert ()__" + e.getMessage());
		} finally {
			this.close(null, this.pstmt, this.conn);
		}
		return status;
	} // insert()

	public boolean modify(Model m) {

		boolean status = false;

		String pageNum = m.get_pageNum();
		String searchType = m.get_searchType();
		String searchText = m.get_searchType();

		int idx = m.get_idx();
		String subject = toLatin(m.get_subject());
		String writer = toLatin(m.get_writer());
		String contents = toLatin(m.get_contents());

		String q = "update board set subject = ?, writer = ?, contents = ?, mod_date = now() where idx = ?";
		this.connDB();

		try {
			this.pstmt = this.conn.prepareStatement(q);
			pstmt.setString(1, subject);
			pstmt.setString(2, writer);
			pstmt.setString(3, contents);
			pstmt.setInt(4, idx);

			int n = pstmt.executeUpdate();

			status = (n > 0) ? true : false;

		} catch (SQLException e) {
			System.out.println("ERR_modify ()__" + e.getMessage());
		} finally {
			this.close(null, this.pstmt, this.conn);
		}
		return status;
	}

	public void hit_up(Model m) {
		try {

			this.connDB();

			String q = "UPDATE board SET hit = hit+1 WHERE idx = ?";

			this.pstmt = this.conn.prepareStatement(q);
			this.pstmt.setInt(1, m.get_idx());
			int n = this.pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("ERR_hit_up ()__" + e.getMessage());
		} finally {
			this.close(null, this.pstmt, this.conn);
		}
	} // hit_up ()

	public boolean delete_one(Model m) {
		boolean status = false;

		int idx = m.get_idx();
		String q = "delete from board where idx = ? ";

		this.connDB();
		try {
			this.pstmt = this.conn.prepareStatement(q);
			this.pstmt.setInt(1, idx);
			int n = this.pstmt.executeUpdate();

			status = (n > 0) ? true : false;

		} catch (SQLException e) {
			System.out.println("ERR_hit_up ()__" + e.getMessage());
		} finally {
			this.close(null, this.pstmt, this.conn);
		}
		return status;
	}

	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("ERR_rs.close()__" + e.getMessage());
			}
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("ERR_pstmt.close()__" + e.getMessage());
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("ERR_conn.close()__" + e.getMessage());
			}
		}
	} // close()

	public String toLatin(String w) {
		try {
			byte[] b = w.getBytes();
			return new String(b, "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERR_toLatin()__" + e.getMessage());
			return null;
		}
	} // toLatin()

	public String toUnicode(String w) {
		try {
			byte[] b = w.getBytes("ISO-8859-1");
			return new String(b);
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERR_toUnicode()__" + e.getMessage());
			return null;
		}
	}	// toUnicode
}
