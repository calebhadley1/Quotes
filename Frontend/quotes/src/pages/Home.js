import AddQuote from "../components/addQuote";
import Navbar from "../components/navbar";
import QuotesList from "../components/quoteslist";

const Home = () => {
    return (
        <section>
            <Navbar/>
            <QuotesList/>
            <AddQuote/>
        </section>
    )
}

export default Home