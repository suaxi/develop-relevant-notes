import Chart from './components/Chart'

const Home = () => {
  return (
    <div>
      <Chart
        title={'用户发表文章数'}
        xAxis={['孙笑川', '药水哥', 'Giao哥']}
        series={[10, 15, 8]}
      />
    </div>
  )
}

export default Home
