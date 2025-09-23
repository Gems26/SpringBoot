import React, { useEffect, useState } from 'react';
import api from './api';

export default function StudentList() {
  const [students, setStudents] = useState([]);
  const [editing, setEditing] = useState(null);
  const [editForm, setEditForm] = useState({});

  const load = async () => {
    const res = await api.get('');
    setStudents(res.data);
  };

  useEffect(() => {
    load();
    const handler = () => load();
    window.addEventListener('refreshStudents', handler);
    return () => window.removeEventListener('refreshStudents', handler);
  }, []);

  const remove = async (id) => {
    if (!window.confirm('Delete student?')) return;
    await api.delete('/' + id);
    load();
  };

  const startEdit = (s) => { setEditing(s.id); setEditForm({...s}); };
  const saveEdit = async (id) => {
    await api.put('/' + id, editForm);
    setEditing(null);
    load();
  };

  return (
    <div>
      <table border="1" cellPadding="8" style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead><tr><th>ID</th><th>Name</th><th>Email</th><th>Course</th><th>Grade</th><th>Actions</th></tr></thead>
        <tbody>
          {students.map(s => (
            <tr key={s.id}>
              <td>{s.id}</td>
              <td>{editing===s.id ? <input value={editForm.name} onChange={e=>setEditForm({...editForm, name:e.target.value})}/> : s.name}</td>
              <td>{editing===s.id ? <input value={editForm.email} onChange={e=>setEditForm({...editForm, email:e.target.value})}/> : s.email}</td>
              <td>{editing===s.id ? <input value={editForm.course} onChange={e=>setEditForm({...editForm, course:e.target.value})}/> : s.course}</td>
              <td>{editing===s.id ? <input value={editForm.grade} onChange={e=>setEditForm({...editForm, grade:e.target.value})}/> : s.grade}</td>
              <td>
                {editing===s.id ? <>
                  <button onClick={()=>saveEdit(s.id)}>Save</button>
                  <button onClick={()=>setEditing(null)}>Cancel</button>
                </> : <>
                  <button onClick={()=>startEdit(s)}>Edit</button>
                  <button onClick={()=>remove(s.id)}>Delete</button>
                </>}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
