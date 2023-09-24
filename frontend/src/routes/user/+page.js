import { error } from '@sveltejs/kit';

export async function load({ params, fetch }){
    const fetchRes = await fetch("https://v2.jokeapi.dev/joke/Any?format=txt");
    if(fetchRes.status === 200){
        const res = await fetchRes.text();
        return { res };
    }
    //lib//if($firstStart || $onReload){
   
        //}
    throw new error(fetchRes.status, fetchRes.statusText);
}