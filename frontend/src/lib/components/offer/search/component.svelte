<script>
    export let data;
    export let subcategory = null;

    import { error } from '@sveltejs/kit';

    (async function load({ fetch }){
        let offers;
        let errors = [];
        console.log("ok");
        try{
            const fetchOffers = await fetch(`http://127.0.0.1:8080/category`, { method: "GET", mode: 'cors'});
        
            const categories = fetchCategories.json();
            const subcategories = fetchSubcategories.json();
            
            console.log('ok');

            return { categories, subcategories }; 
        }
        catch (error){
            console.error("error: ", error);
        }
        throw new error(fetchCategories.status, fetchCategories.statusText);
    })

</script>

<div>
    {#if subcategory !== null}
        {#each data.offers as offer}
            
        {/each}
    {:else}
        <div class="flex flex-auto pt-16 justify-center align-middle">
            <p class="">
                Выберите подкатегорию чтобы начать посмотреть предложения
            </p>
        </div>
    {/if}
</div>