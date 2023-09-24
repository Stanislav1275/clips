export const actions = {
    async create({ request, fetch }){
        const data = await request.formData();
        const email = data.EMAIL;
        const password = data.PASSWORD;
        const res = await fetch(`http://127.0.0.1:8080/auth/sign_in`, { method: "POST"});
    }
}