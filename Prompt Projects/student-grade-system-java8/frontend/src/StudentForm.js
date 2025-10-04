import React, { useState } from 'react';
import api from './api';

export default function StudentForm() {
  const [form, setForm] = useState({ name: '', email: '', course: '', grade: '' });

  const submit = async (e) => {
    e.preventDefault();
    try {
      await api.post('', form);
      setForm({ name: '', email: '', course: '', grade: '' });
      window.dispatchEvent(new Event('refreshStudents'));
    } catch (err) {
      alert('Error: ' + (err.response?.data || err.message));
    }
  };

  return (
    <form onSubmit={submit} style={{ marginBottom: 20 }}>
      <input required placeholder="Name" value={form.name} onChange={e=>setForm({...form, name:e.target.value})} />
      &nbsp;
      <input required placeholder="Email" value={form.email} onChange={e=>setForm({...form, email:e.target.value})} />
      &nbsp;
      <input placeholder="Course" value={form.course} onChange={e=>setForm({...form, course:e.target.value})} />
      &nbsp;
      <input placeholder="Grade" value={form.grade} onChange={e=>setForm({...form, grade:e.target.value})} />
      &nbsp;
      <button type="submit">Add Student</button>
    </form>
  );
}
