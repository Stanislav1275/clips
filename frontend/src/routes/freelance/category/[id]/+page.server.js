import { error } from '@sveltejs/kit';

export async function load({ fetch, params }){
    let categories;
    let subcategories;
    let errors = [];
    let id = params.id;
    try{
        const fetchCategories = await fetch(`http://127.0.0.1:8080/category`, { method: "GET", mode: 'cors'});
        const fetchSubcategories = await fetch(`http://127.0.0.1:8080/subcategory`, { method: "GET", mode: 'cors' }); 

        const categories = await fetchCategories.json();
        const subcategories = await fetchSubcategories.json();

        return { categories, subcategories, id }; 
    }
    catch (error){
        console.error("error: ", error);
    }
    throw new error(fetchCategories.status, fetchCategories.statusText);
}