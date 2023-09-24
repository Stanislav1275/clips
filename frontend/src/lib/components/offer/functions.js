import { error } from "@sveltejs/kit";
export async function getOffer(ID){
    await fetch(`http://127.0.0.1:8080/offer/${ID}`, { method: "GET", mode: 'cors'}).then(res => res.json().then(data => {
        return { data };
        
    }).catch(error)).catch(error);
}