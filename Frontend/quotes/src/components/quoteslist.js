import axios from "../api/axios";
import { useState } from "react";
import '../css/Table.css'

const QuotesList = () => {

    const QUOTES_URL = '/quote'; // GET/POST/DELETE
    
    const [noQuotes, setNoQuotes] = useState(false);
    const [quotes, setQuotes] = useState([]);

    async function deleteQuote(id) {
        try {
            const response = axios.delete(QUOTES_URL+"/"+id);
            getQuotes();
        } catch(err) {
            console.log(err);
        }
    }

    const getQuotes = async (e) => {
        try {
            //request to backend
            const response = await axios.get(QUOTES_URL);
            
            setQuotes(response.data)
            if(response.data.length === 0)
                setNoQuotes(true);
        } catch(err) {
            console.log(err);
        }
    }

    if(quotes.length === 0 && !noQuotes)
        getQuotes();
    return (
        <>
        <div className="table">
            <h5 className="title">Quotes</h5>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Date</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    {quotes.map((elem)=>(
                        <tr key={elem.id}>
                        <td>{elem.quoteName}</td>
                        <td>{elem.quoteCategory}</td>
                        <td>{elem.quoteDate}</td>
                        <td><input type="button" value="Remove" onClick ={() => { if (window.confirm('Are you sure you wish to remove this quote: '+elem.quoteName+"?")) deleteQuote(elem.id) } }/></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
        </>
    );
}

export default QuotesList;