<script>
    import { page } from '$app/stores';
    import sadFire from '$lib/images/sad-fire.webp';
</script>

<div class="flex flex-col w-2/3 mx-auto self-center items-center justify-center">
    <img class="my-10 w-1/3 mx-auto" src={sadFire} alt="sad fire">
    <h1 class="text-9xl mt-20">{$page.status}</h1>
    <p class="mt-12 text-2xl">{$page.error?.message}</p>
</div>