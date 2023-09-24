import { error } from "@sveltejs/kit";
export async function getOfferByID(fetch, ID){
    await fetch(`http://127.0.0.1:8080/offer/${ID}`, { method: "GET", mode: 'cors'}).then(res => res.json().then(data => {
        return data;
        
    }).catch(error)).catch(error);
}

export async function getProfileByID(fetch, ID){
    await fetch(`http://127.0.0.1:8080/user/${ID}`, { method: "GET", mode: 'cors'}).then(res => res.json().then(data => {
        return data;
        
    }).catch(error)).catch(error);
}

export async function getFileEntityByID(fetch, ID){
    await fetch(`http://127.0.0.1:8080/info/${ID}`, { method: "GET", mode: 'cors'}).then(res => res.json().then(data => {
        return data;
    }).catch(error)).catch(error);
}

export async function getTiersByOffer(fetch, ID){
    await fetch(`http://127.0.0.1:8080/job_tier?ID=${ID}`, { method: "GET", mode: 'cors'}).then(res => res.json().then(data => {
        return data;
    }).catch(error)).catch(error);
}