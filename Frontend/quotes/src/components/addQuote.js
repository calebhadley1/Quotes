import { useRef, useState, useEffect, useContext } from 'react';
import axios from '../api/axios';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import '../css/AddQuote.css'

const AddQuote = () => {

    const QUOTES_URL = '/quote'; // GET/POST/DELETE

    const nameRef = useRef();
    const [name, setName] = useState('');
    const [category, setCategory] = useState('BOOK');
    const [startDate, setStartDate] = useState(new Date());

    const handleCategory = (e) => {
        console.log(e);
        setCategory(e.target.value)
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log(name,category,startDate);
        try {
            const response = await axios.post(QUOTES_URL,
                JSON.stringify({quoteName: name, quoteCategory: category, quoteDate: startDate}),
                {
                    headers: { 'Content-Type': 'application/json'},
                    withCredentials: true
                }
            )
            console.log(response)
        } catch(err) {
            console.log(err);
        }
    }

    return (
        <div className='form-box'>
        <h6>Add Quote</h6>
        <form onSubmit={handleSubmit}>
            <label htmlFor="quoteName">
                Quote
                <input type="text" id="name" ref={nameRef} onChange={(e) => setName(e.target.value)} value={name} required></input>
            </label>
            <label htmlFor="quoteCategory">
                Category
                <div className='custom-select'>
                    <select name="status" id="status" onChange={handleCategory}>
                        <option value="BOOK">Book</option>
                        <option value="JOURNAL">Journal</option>
                        <option value="WEBSITE">Website</option>
                        <option value="OTHER">Other</option>
                    </select>
                </div>
            </label>
            <label>
                Date
                <DatePicker selected={startDate} onChange={(date) => setStartDate(date)} />
            </label>
            <button className='submit-button'>Submit</button>
        </form>
        </div>
    )
}

export default AddQuote;