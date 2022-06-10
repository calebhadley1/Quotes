import AddQuote from "../components/addQuote";
import Navbar from "../components/navbar";
import QuotesList from "../components/quoteslist";
import '../App.css';

const Home = () => {
    return (
        <div className="App">
            <Navbar/>
            <h1>Home page</h1>
            <QuotesList/>
            <AddQuote/>
        </div>
    )
}

export default Home