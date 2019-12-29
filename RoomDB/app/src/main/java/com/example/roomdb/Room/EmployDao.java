package com.example.roomdb.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface EmployDao {

    //employee
    @Insert(onConflict = REPLACE)
    void insertEmploy(Employee employee);
    @Insert(onConflict = IGNORE)
    void insertOrReplaceEmploy(Employee... employees);
    @Update(onConflict = REPLACE)
    void updateEmploy(Employee employee);
    @Query("DELETE FROM Employee")
    void deleteAll();
    @Query("SELECT * FROM Employee")
    public List<Employee> findAllEmploySync();

    @Query("select * from Employee where id_company=:id_company")
    List<Employee> getEmployeeByCompany(int id_company);


    //company
    @Insert(onConflict = REPLACE)
    void insertCompany(Company company);
    @Query("select * from Company")
    List<Company> getAllCompany();



}
