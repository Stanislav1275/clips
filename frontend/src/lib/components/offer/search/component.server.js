import { error } from '@sveltejs/kit';

export async function load({ fetch }){
    let offers;
    let errors = [];
    try{
        const fetchOffers = await fetch(`http://127.0.0.1:8080/category`, { method: "GET", mode: 'cors'});
        
        const categories = fetchCategories.json();
        const subcategories = fetchSubcategories.json();

        return { categories, subcategories }; 
    }
    catch (error){
        console.error("error: ", error);
    }
    throw new error(fetchCategories.status, fetchCategories.statusText);
}