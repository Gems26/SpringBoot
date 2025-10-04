import React from 'react';
import StudentList from './StudentList';
import StudentForm from './StudentForm';

export default function App() {
  return (
    <div style={{ maxWidth: 900, margin: '40px auto', fontFamily: 'Arial, sans-serif' }}>
      <h1>Student Grade System</h1>
      <StudentForm />
      <StudentList />
    </div>
  );
}
