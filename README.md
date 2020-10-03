# @Hibernate


## 1. Hibernate Overview
#### Overview
+ Author: Gavin King
+ ORM
	+ Object mapping Relation Framework
	+ Match:		Domain Object to Relation Table
	+ Mismatch
		+ Domain Inherit, Relation do not have
		+ Domain has many to many reference, Relation has middle table
		+ Domain has bi-direction, Relation has many to one
		+ Domain has precise datatype, Relation has week datatype
+ Database Independent
	+ All database implement sql standard
	+ Every database has unique feature
+ Hibernate vs SQL
	+ Hibernate: Object
	+ SQL: Table
+ DAO Model

```	     
				 Persistent Object--------------|
				 /    	|		|				|
				/     	|		|				|
	Business Layer -- Hibernate Session API		Object-Relation Mapping (XML)
						|		|				|
						|		|				|
					Relation Database ----------|
```

#### Libraries
- hibernate3.jar
- lib\required\*.jar
- slf4j-nop-version#.jar[compatible with hiberante slf4j-api-version
 
#### Hibernate Configuration
```
<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <!-- Database connection settings -->
        <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="connection.url">jdbc:mysql://localhost/hibernate</property>
        <property name="connection.username">root</property>
        <property name="connection.password">root</property>
        <!-- JDBC connection pool (use the built-in) <property name="connection.pool_size">1</property> -->
        <!-- SQL dialect -->
        <property name="dialect">org.hibernate.dialect.MySQLDialect</property>
        <!-- Enable Hibernate's automatic session context management <property name="current_session_context_class">thread</property> -->
        <!-- Disable the second-level cache  -->
        <property name="cache.provider_class">org.hibernate.cache.NoCacheProvider</property>
        <!-- Echo all executed SQL to stdout -->
        <property name="show_sql">true</property>
        <!-- Drop and re-create the database schema on startup -->
        <property name="hbm2ddl.auto">update</property>
        <!-- Dao mapping declaration -->
        <mapping resource="Person.hbm.xml" />
    </session-factory>
</hibernate-configuration>
```
#### Entity Annotation
```
@Entity
public class Teacher {
	
    @Id
	public int getId() { return id; }

    @Column(name="_name")
    public String getName() { return name; }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getBirthDate() { return birthDate;}
    
    @Enumerated(EnumType.STRING)
	public Gender getGender() { return gender; }
}
```
#### Hibernate Util [Session Factory vs Session] 
- Overview
	+ Build SessionFactory via Hibernate Util
	+ Open/Close Session via Hibernate Util
- SessionFactory
	+ create session for request
	+ Thread Safety:	multiple thread can access
	+ Heavy Object
		+ more resource to create and destory
		+ one Database map to one SessionFactory
Session
	+ similar connection
	+ Not Thread Safe:	avoid multiple thread access
	+ Light Object:	less resource to create and destory
		
```
// session factory
Configuration cfg = new Configuration().configure();
ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
		.applySettings(cfg.getProperties()).buildServiceRegistry();
sessionFactory = cfg.buildSessionFactory(serviceRegistry);


// session crud
Session session = HibernateUtil.openSession();
Transaction tx = session.beginTransaction();
Person person = null;
try {
	session.save(person);
	Query query = session.createQuery("from Person");
				list = (List<Person>) query.list();	
	session.update(person);
	person = (Person) session.get(Person.class, Integer.valueOf(id));
	session.createQuery("from Person")
					.setFirstResult(int)
					.setMaxRecords(int)
	tx.commit();
} catch (Exception ex) {
	if (null != tx) {
		tx.rollback();
	}
} finally {
	HibernateUtil.close(session);
}
```
***
		
		
## 2. DataType
### Java Hibernate SQL
|  Java	| Hibernate	|	SQL   |
| ------:	|	---------:|	-----:|
| String	|	String		| Varchar |
| int		|	int			|	int		|
| long		| long			| Big int |
| char		| character	| char(1) |
| boolean	| boolean 	| bit		|
| byte[]	| binary		|	blob	|
| Date		|	date		|	date	|
| Timestamp	|	timestamp	|	timestamp|
#### Hibenrnate Tools
- POJO to  Hibernate Mapping XML 
- Hibernate Mapping XML to Database



## 3. List vs Iterator
#### List
- Eager loading, return list of read object
- Deletion:	1 query + n delete

#### Iterator
- Lazy Loading, return iterate of object Id
- Deletion:	n query + n delete

***			
	
	
	
## 4. RelationMapping
##### ID Generation
```
// native
@GeneratedValue
public int getId() {
	return id;
}

// seq
@Id
@SequenceGenerator(name="teacherSEQ", sequenceName="teacherSEQ")
public int getId() {
	return id;
}

// table
@javax.persistence.TableGenerator(
	    name="Teacher_GEN",
	    table="GENERATOR_TABLE",
	    pkColumnName = "pk_key",
	    valueColumnName = "pk_value",
	    pkColumnValue="Teacher",
	    allocationSize=1
	)
@Id
@GeneratedValue(strategy=GenerationType.TABLE, generator="Teacher_GEN")
public int getId() {
	return id;
}

```

#### One to Many
- Delete Many Party to remove foreign key constraint
- Delete One Party

```
public class User {
	@ManyToOne
	public Group getGroup() { return group;}
}

public class Group {
	@OneToMany(mappedBy = "group")
	public Set<User> getUsers() { return users; }
}
```
	
#### Many to Many
```
public class Teacher{
	@ManyToMany
	@JoinTable(name = "t_teacher_student",
	joinColumns = {@JoinColumn(name = "teacher_id")},
	inverseJoinColumns = {@JoinColumn(name = "student_id")}
	public Set<Student> getStudents()
}
	
public class Student {
	@ManyToMany
	@JoinTable(name = "t_teacher_student",
	joinColumns = {@JoinColumn(name = "student_id")},
	inverseJoinColumns = {@JoinColumn(name = "teacher_id")}
	public Set<User> getTeachers() { return teachers; }
}
```
#### One to One
```
// primary key
public class Husband {
	@OneToOne
	@PrimaryKeyJoinColumn
	public Wife getWife() {	return wife; }
}
			
			
// foreign key
public class Husband {	
	@OneToOne
	@JoinColumns(
		{
			@JoinColumn(name="wifeId", referencedColumnName="id"),
			@JoinColumn(name="wifeName", referencedColumnName="name")
		}
	)
	public Wife getWife() { return wife;}
}

```
***
	
	
## 5. Hiberante Status
#### Session Cache
- Overview
	+ 1st level cache(txn/thread), persistent object has unique OID
	+ 2nd level cache is SessionFactory
	+ reduce Database Operation
	+ syn cache Object with database
	+ if cache object is not sync, database buffer the update and syn update together
- Operation
	+ save(): persistent Object to database, Object exist till end of session
	+ load(): load Object from cache, if no Obejct in cache, load Object from DB
	+ update(): put Object into Session, commit update during flush
- Flush
	+ commit(): clear cache, then commit tx
	+ flush(): clear cache
- Get vs Load
	+ Get
		+ no exist object return null
		+ eger loading, instance object
	+ Load	
		+ no exist object throws exception
		+ lazy loading, only instance object while using
- Status
	+ Transient:	new Object not persistent
	+ Persisent:	Persistent Object and in Session
	+ Detached:	Persistent Object but not in Session
	
```
				
	|--- new -- Transient---------------------------|
				|		|							|
				|		|							|
			   save		delete						|
		saveOrUpdate	|							|
				|		|							|
				Persistent						Garbage Collection 
				|		|							|
			evict()		update()					|
			close()		saveOrUpdate()				|
			clean()		lock()						|
				|		|							|
				 Detached --------------------------|	
```
				 
		
## 6. Sorting
#### Order by Cfg
- Send SQL with 'Order by'

```
<map name="students" table="student" cascade="all" order-by="name asc">
	<key column="team_id"></key>
	<index column="card_id" type="java.lang.String"></index> <!-- 指定的是Map中的key值 -->
	<one-to-many class="com.dbs.hibernate.mapping.map.Student"/>
</map>
```
#### Sort in Memory
- Sort:	unsorted, natural

```
<set name="students" table="student" sort="natural">
	<key column="team_id"></key>
	<element column="name" type="string"></element>
</set>
```
#### Comparator

```
<set name="students" table="student" sort="com.dbs.hibernate.mapping.set.StudentComparator" cascade="all">
	<key column="team_id"></key>
	<one-to-many class="com.dbs.hibernate.mapping.set.Student"/>
</set>	
```
***


## 7. Composite Column
#### Composite Primary Key 
- Class implments Serializable for query
- Override hashCode to equals
- Implments Key Class
- Override hashCode to equals in Key Class

 
```	
// Annotation in class
@Embeddable
public class TeacherPK implements java.io.Serializable{}

@Entity
public class Teacher {	
	@EmbeddedId
	public TeacherPK getPk() { return pk; }
}


// Annotation in ID, Composite ID is (Id + Name)
@Entity
@IdClass(TeacherPK.class)
public class Teacher {
	@Id
	public int getId() {
		return id;
	}
	@Id
	public String getName() {
		return name;
	}
}

```
#### Standard Composite Column 

```
<!--Single Table-->
<hibernate-mapping>
	<class name="com.dbs.hibernate.compositecolumn.single.Student" table="student">
		<id name="id" column="id" type="string">
			<generator class="uuid"></generator>
		</id>
		<property name="name" column="name" type="string"></property>
		<!-- Contains home address and school address -->
		<component name="address" class="com.dbs.hibernate.compositecolumn.single.Address">
			<property name="homeAddress" type="string"></property>
			<property name="schoolAddress" type="string"></property>
		</component>
	</class>
</hibernate-mapping>

<!--Seperate Table-->
<hibernate-mapping>
	<class name="com.dbs.hibernate.compositecolumn.seperate.Student"
		table="student">
		<id name="id" column="id" type="string">
			<generator class="uuid"></generator>
		</id>
		<property name="name" column="name" type="string"></property>
		<set name="contacts" table="contact">
			<key column="student_id"></key>
			<composite-element
				class="com.dbs.hibernate.compositecolumn.seperate.Contact">
				<property name="method" type="string"></property>
				<property name="address" type="string"></property>
			</composite-element>
		</set>
	</class>
</hibernate-mapping>
```
	
	

## 8. Inherit
#### All Child class has common table

```
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("person")
public class Person {}
@Entity
@DiscriminatorValue("student")
public class Student extends Person {}
@Entity
@DiscriminatorValue("Teacher")
public class Teacher extends Person {}
```
#### Super class and Child class both has own table
```
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Person {}

public class Student extends Person {}
public class Teacher extends Person {}
```
***	
	
## 9. HQL
#### Navigation
- Object navigate: Student.getCourse()
- OID: get(), load()
- HQL: Hibernate Query Language
- QBC: Query By Criteria
#### HQL
- Chain Program
- Query + Parameters
 
```
// HQL Basic
"from Category"
"from Category c order by c.name desc"
"from Category c where c.name > 'c5'"
"from Msg m where m.id between 3 and 5"
"from Msg m where m.id in (3,4, 5)"
"from Msg m where m.cont is not null"
"select distinct c from Category c order by c.name desc"

// HQL place holder
"from Category c where c.id < :max  and c.id >:min"
q.setParameter("min", 2);  q.setParameter("max", 8);
q.setInteger("min", 2);  q.setInteger("max", 8);

// HQL Pagetation
"from Category c order by c.name desc"
q.setMaxResults(4);
q.setFirstResult(2);

// HQL Object[]
"select c.id,  c.name from Category c order by c.name desc"
List<Object[]> categories = (List<Object[]>) q.list();
for (Object[] o : categories) {
	System.out.println(o[0] + "-" + o[1]);
}

// HQL Join Table
"from Topic t where t.category.id = 1"

// HQL Object Wrapper
"select new com.bjsxt.hibernate.MsgInfo(m.id, m.cont, m.topic.title, m.topic.category.name) from Msg"
Note: MsgInfo is not EJB Entity, constructor .MsgInfo(m.id, m.cont, m.topic.title, m.topic.category.name) exists

// HQL Join
"select t.title, c.name from Topic t join t.category c "
Note: t.category > Catagory since maybe more than tow attribute is Catagory class

// HQL Where && UniqueResult()
"from Msg m where m = :MsgToSearch " 
Msg m = new Msg();
m.setId(1);
q.setParameter("MsgToSearch", m);
Msg mResult = (Msg) q.uniqueResult();

// HQL Function
count(*) | max(m.id) | min(m.id) | avg(m.id) | sum(m.id)

// HQL Empty
"from Topic t where t.msgs is empty"

// HQL like
"from Topic t where t.title like '%5'"

// HQL String Function
lower(t.title), upper(t.title), trim(t.title), concat(t.title, '***') , length(t.title)

// HQL Calculate Function
abs(t.id), sqrt(t.id), mod(t.id, 2)
 
// HQL DateTime
current_date | current_time | current_timestamp

// HQL group&&having
"select t.title, count(*) from Topic t group by t.title having count(*) >= 1"

// HQL subquery
"from Topic t where t.id  > (select avg(t.id) from Topic t)"
"from Topic t where t.id > ALL (select t.id from Topic t where mod(t.id, 2)= 0)
"from Topic t where not exists (select m.id from Msg m where m.topic.id=t.id)"

// HQL Update
update Topic t set t.title = upper(t.title)"
```
#### HQL NamedQuery
```
@NamedQueries({
			@NamedQuery(name="topic.selectCertainTopic", query="from Topic t where t.id = :id")
		})
Program:
Query q = session.getNamedQuery("topic.selectCertainTopic");
q.setParameter("id", 5);
```
#### HQL Native SQL
```
SQLQuery q = session.createSQLQuery("select * from category limit 2,4")
				.addEntity(Category.class);
List<Category> categories = (List<Category>) q.list();
```
#### QBC
```
Criteria c = session.createCriteria(Topic.class) 
				.add(Restrictions.gt("id", 2)) 
				.add(Restrictions.lt("id", 8)) 
				.add(Restrictions.like("title", "t_"))
				.createCriteria("category"). //join catagory
				add(Restrictions.between("id", 3, 5));
```
***	

## 10. Transaction
#### Overview
- Atomicity:		all success all failed
- Consistency:	database consistency, primary key, foreign key
- Isolation:		txn isolate
- Durability:		record persistent

#### Problem
- lost update
- dirty read[read non-commit trans]
- non-repeatable read[two different result]
- phantom read[new record]

#### Isolation level
- read-uncommited: dirty read, phantom read, non-repeatable read
- read-commited: phantom read, non-repteable read
- read-repteable: phantom read
- serializable
 
```
<property name="hibernate.connection.isolation">2</property>
```

#### Pessimistic vs Optimistic
- Pessimistic Lock for read-commited
- session.load(Account.class, 1, LockOptions.UPGRADE);
- SQL select ... for update; update ....
- LockMode:
	+ None:  no lock (hibernate)
	+ Read:	 lock for read (hibernate)
	+ write: lock for write (hibernate)
	+ upgrade|no_wait:	(hibernate)
- Optimistic Lock for read-commited

```
Version:		increment version
Timestamp
@Entity
public class Account {
	private int version;
	@Version
	public int getVersion() {	return version; }
}
```
***	
			
		
## 11. 2nd level Cache
#### Overview
- Session Cache
- SesionFactory Cache

#### Cache level
- transactional:			repeated read, for frequence read/write, less update
- read-write				timestamp trx, for frequence read/write, less update
- nonstrict-read-write	set short expire time otherwise dirty read may happen
- read-only				data is not changed

#### Library
- ehcache-core-2.4.3.jar
- hibernate-ehcache-4.2.0.Final.jar
- slf4j-api-1.6.1.jar

#### Configure

```
<!--hibernate.cfg.xml-->
<property name="cache.use_second_level_cache">true</property>
<property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>

<!--ehcache.xml-->
<ehcache>
	<diskStore path="java.io.tmpdir"/>
	<defaultCache
		maxElementsInMemory="10000"
		eternal="false"
		timeToIdleSeconds="120"
		timeToLiveSeconds="1200"
		overflowToDisk="true"
		/>
</ehcache>
```
***		
	
		
## 12. Interceptor and Listener
#### Interceptor
- Oveview
	+ implments EmptyInterceptor
	+ Session Level
	+ Session Factory Level
- Implememtation
	+ Override onLoad, onSave, onDelete
#### Listener (Event)
- Extend DefaultLoadEventListener, Override onLoad
- DefaultSaveEventListener, Override performSaveOrUpdate

```
<event type="load">
	<listener class="com.dbs.hibernate.interceptor.event.TestLoadListener" />
</event>
<event type="save">
	<listener class="com.dbs.hibernate.interceptor.event.TestSaveListener" />
</event>
```
***
	
	
	
## 13. Connection Pool
#### Method
- Connection Pool: C3P0, Apache DBCP
```
<property name="hibernate.c3p0.min_size">10</property>
<property name="hibernate.c3p0.max_size">40</property>
<property name="hibernate.c3p0.timeout">200</property>
<property name="hibernate.c3p0.max_statements">30</property>
<property name="hibernate.c3p0.idle_test_period">100</property>
```