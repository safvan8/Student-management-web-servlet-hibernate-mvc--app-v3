package in.ineuron.dao.dboperations;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.pojo.Student;
import in.ineuron.util.HibernateUtil;

// class to perform insert operations
public class InsertStudent
{
	private static InsertStudent insertStudent = null;

	// making class singleton
	private InsertStudent()
	{
	}

	public static InsertStudent getInsertStudent()
	{
		System.out.println("InsertStudent.getInsertStudent()...........\n");
		if (insertStudent == null)
			insertStudent = new InsertStudent();
		return insertStudent;
	}

	// to insert student record in to DB
	public Integer save(Student student)
	{
		System.out.println("InsertStudent.save()................\n");
		
		// getting session from utility class
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		boolean isOperationSuceess = false;
		int generatedSId = 0;

		try
		{
			transaction = session.beginTransaction();

			// inserting object to DB
			// if we use save() method then Hibernate Software will insert a record in DB
			// and return generated primary key value,
			generatedSId = (Integer) session.save(student);

			isOperationSuceess = true;

		} catch (Exception e)
		{
			e.printStackTrace();
			isOperationSuceess = false;
		} finally
		{
			if (isOperationSuceess)
				transaction.commit();
			else
				transaction.rollback();

			session.close();
		}

		// returning generated PrimaryKey
		return generatedSId;
	}
}
